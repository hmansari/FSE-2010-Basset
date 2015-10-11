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

import scala.actors.{Actor,Exit}
import scala.actors.Actor._

import scalaexamples.util.Main

object Leader extends Main  {
  
  def default() = {
    input = 3
  }
  
  var ps: List[LProcess] = Nil
  
  def mainBody() = {
    var i,j,n = input.get
  
    var ids = List.range(0,n)
  
    var actor: Actor = null
    ps = for(i <- ids) yield new LProcess
  
  
    for(id <- ids)
      ps(id) init (ps((id+1)%n), id)
  }
}

trait Message
case class LMsg(_type: Int, id: Int) extends Message

object LProcess {
  val FIRST = 0
  val SECOND = 1
  
  var counter = 0
  
  def incCounter() {
    counter = counter + 1
    if(counter == Leader.input.get-1) {
      val active = Leader.ps.filter(_.active)
      assert(active.size == 1)
    }
  }
}

class LProcess extends Actor {
  import LProcess._
  private var number, maxi, neighborR: Int = _
  var active = false
  private var right: LProcess = _
  
  
  def init(right: LProcess, id: Int) {
    this.right = right
    this.maxi = id
    this.active = true
    right ! LMsg(FIRST,id) 
    start
  }
  
  def act() = loop /*While(active)*/ {
    react {
      
      case msg @ LMsg(FIRST,id) =>
        number = id
        if(active && number != maxi) {
          right ! LMsg(SECOND,number)
          neighborR = number
        } else if(!active) 
          right ! LMsg(FIRST,number)
        
      case msg @ LMsg(SECOND,id) =>
        number = id
        if(active) {
          if(neighborR > number && neighborR > maxi) {
            maxi = neighborR
            right ! LMsg(FIRST,neighborR)
          } else {
            active = false
            incCounter()
          }
        } else 
          right ! LMsg(SECOND,number)
    }
  }
  
}
