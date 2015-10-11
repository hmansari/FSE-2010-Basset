//
// Copyright (C) 2010 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
//
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
//
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//
package scalaexamples.dcute

import scala.actors.Actor
import scala.actors.Actor._

import scalaexamples.util.Main

object SpinSort extends Main {
  
  def default() = {
    input = 4
  }
  
  var N: Int = _
  
  def mainBody() = {
    N = input.get
    var a: Array[PMiddle] = new Array(N) 
    var tmp: Actor = null
    for(i <- List.range(0,N)) {
      a.update(N-i-1, new PMiddle(tmp,N-i))
      tmp = a(N-i-1)
      tmp.start
    }
    
    new PLeft(tmp).initialize()
  }
  
}

case class SpinSMsg(v: Int) 


class PLeft(private val out: Actor) extends Actor {
  
  private val rand: java.util.Random = new java.util.Random(9999)
  
  def initialize() {
    var counter = 0
    do {
      out ! SpinSMsg(rand.nextInt(1000))
      counter = counter + 1
    } while(counter < SpinSort.N)
    start
  }
  
  def act() = loop {
    react {
      case junk => println(this+" got "+junk)
    }
  }
}


class PMiddle extends Actor {
  private var out: Actor = _ 
  private var counter: Int = _
  var myval: Int = _
  private var nextval: Int = _
  private var first = true


  
  def this(out: Actor,procnum: Int) {
    this()
    this.out = out
    this.counter = SpinSort.N - procnum
  }
  
  def act() = loop {
    react {
      case SpinSMsg(v) =>
        if(first) {
          myval = v
          first = false
        } else if(counter > 0) {
          nextval = v
          if(nextval >= myval)
            out ! SpinSMsg(nextval)
          else {
            out ! SpinSMsg(myval)
            myval = nextval
          }
          counter = counter -1
        }
    }
  }
}