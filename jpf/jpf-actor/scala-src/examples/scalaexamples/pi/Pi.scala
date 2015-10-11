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
package scalaexamples.pi

import gov.nasa.jpf.actor.env.{TestDriver,EmptyEnvironment}

import scala.actors.Actor
import scala.actors.Actor._

import scalaexamples.util.Main

/** 
 * Simple example using a split/merge scheme to calculate the value of pi.
 * Translated from a MPI example found at:
 * http://www-unix.mcs.anl.gov/mpi/usingmpi/examples/simplempi/main.htm
 *
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */

case class Intervals(i:Int)
case class Sum(d:Double)
case object Stop

object Pi extends TestDriver with Main {
  def default() = {
    input = 3
  }
  
  def mainBody() = {
    setEnvironment(new EmptyEnvironment)
    val ws = List.range(0,input.get).map(new Worker(_,input.get))
    new Master(ws).start
    //val m = new Master(ws).start
    //m !? 'start
  }
  
}

class Master(workers: List[Worker]) extends Actor {
  
  def act() = { //react {
    //case 'start =>
    val n = 1000

    workers.foreach( w => {w ! Intervals(n); link(w) })

    var pi = 0.0
    workers.foreach( w => receive {
      case Sum(p) => pi += p
      case err => error(err.toString)
    })

    //println("PI ~= "+pi)
    //println("error = "+ Math.abs(pi - Math.Pi))

    workers.foreach( w => w ! Stop)
    exit('stop)
  }
  
//  override def exit(s: AnyRef) = {
//    println("ok master")
//    super.exit(s)
//  }
  
}

class Worker(id: Int, nbWorkers: Int) extends Actor {
  start
  
  def act() = loop { react {
    case Intervals(n) => {
      val h = 1.0 / n
      val sum = List.range(id,n+1,nbWorkers).map(i => {
			  val x = h * (i - 0.5)
			  4.0 / (1.0 + x*x)
      }).
        reduceLeft( (x,y) => x+y )
      reply(Sum(h * sum))
		}
    case Stop => exit('stop)
  }}
  
//  override def exit(s: AnyRef) = {
//    println("ok worker")
//    super.exit(s)
//  }
}
