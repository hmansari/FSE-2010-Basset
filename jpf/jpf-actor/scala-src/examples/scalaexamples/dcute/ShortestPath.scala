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

import scalaexamples.util.Main

object ShortestPath extends Main {
  def default() = {
    input = 3 //3, 4 or 5
  }
  
  def mainBody() = {
    val N = input match {
      case None => error("Input needs to be defined")
      case Some(n) => n
    }  
    
    assert(N >= 3 && N <= 5)
  
    val a0 = new SPActor
    val a1 = new SPActor
    val a2 = new SPActor
    val a3 = new SPActor
    val a4 = new SPActor
  
    if(N == 3) {
      a0.addNeighbor(a1, 3)
      a1.addNeighbor(a0, 3)
      a0.addNeighbor(a2, 10)
      a2.addNeighbor(a0, 10)
      a1.addNeighbor(a2, 6)
      a2.addNeighbor(a1, 6) 
      a0 send (SpMsg(null,0,0), a0) 
    } else if(N == 4) {
      a0.addNeighbor(a1,10)
      a1.addNeighbor(a0,10)
      a0.addNeighbor(a2,10)
      a2.addNeighbor(a0,10)
      a0.addNeighbor(a3,10)
      a3.addNeighbor(a0,10)
      a1.addNeighbor(a3,10)
      a3.addNeighbor(a1,10)
      a2.addNeighbor(a3,10)
      a3.addNeighbor(a2,10)
      a0 send (SpMsg(null,0,0), a0)
    } else {
      a0.addNeighbor(a1, 10)
      a1.addNeighbor(a0, 10)
      a0.addNeighbor(a2, 10)
      a2.addNeighbor(a0, 10)
      a1.addNeighbor(a3, 10)
      a3.addNeighbor(a1, 10)
      a4.addNeighbor(a0, 10)
      a4.addNeighbor(a1, 10)
      a1.addNeighbor(a4, 10)
      a0.addNeighbor(a4, 10)
      a4 send (SpMsg(null,0,0), a4)
    }
    List(a0,a1,a2,a3,a4).slice(0,N).foreach(_.start)
  }
  
}

trait Msg
case class SpMsg(sender: SPActor, d: Int, w: Int) extends Msg

import scala.actors.Actor
import scala.actors.Actor._

class SPActor extends Actor {
  private var D = -1
  private var N: SPActor = null
  
  private var neighbors: List[(Actor,Int)] = Nil
  
  def addNeighbor(n: SPActor, d: Int) {
    neighbors = neighbors ::: List((n,d))
  }
  
  def act() = loop {
    react {
      case msg @ SpMsg(sender,d,w) =>
        if(D == -1 || D > (d + w)) {
          D = (d + w)
          N = sender
          for((spProcess,nw) <- neighbors) 
            spProcess ! SpMsg(this,D,nw)
        } /*else {
          println(msg)
          System.out.println(this + ": Current distance to X = " + D);
        }*/
    }
  }
}