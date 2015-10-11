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
package scalaexamples.clientserver

/**
 * A simple client server example based on a ScalaWiki example that contained an 
 * atomicity violation bug. This example is also used to show state pruning 
 * thanks to the specific state serialization implemented as part of the 
 * framework. 
 * 
 * When the state linearization is active, this should be seeing the following 
 * traces' tree:
 * 
 *                             s0
 *                              |
 *                              |
 *                              |
 *														  0
 *                            5,value
 * 														 0,0
 *                          /         \
 *                         /           \
 *                        /         		\
 * 											5		             0
 * 										value		        5,reply_0
 *										 0,0		          0,0
 * 											|                |        \
 * 											|                |         \
 * 											|                |          \
 * 										  5                5           0
 * 									 reply_5           reply_0     5,value
 * 										 0,0              0,0          0,0
 * 											|                |       /    |
 * 											|                |     /      |
 * 											|                |   /        |
 * 											5                5            0
 * 									  value            value        5,reply_0
 * 										 5,0              0,0          0,0
 * 											|                |            |        \
 * 											|                |            |         \
 * 											|                |            |          \
 * 											5                5            5           0
 * 									  reply_5         reply_5       reply_0      5,shutdown
 * 										 5,0              0,0          0,0         0,0
 * 											|                |            |      /    |
 * 											|                |            |     /     |
 * 											|                |            |    /      |
 * 											5                5            5           |
 * 									 shutdown         shutdown     shutdown       |
 * 										 5,5              0,5          0,0          |
 * 											|                |            |           |
 * 											|                |            |           |
 * 											|                |            |           |
 * 											5                5            5           0
 * 										 5,5              0,5          0,0          5
 * 																																0,0
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */

/***
object ClientServer extends Application {
  
  val client = actor {
    server ! 5
    val v1 = (server !? "value").asInstanceOf[Int]
    println("value v1 = "+v1)
    val v2 = (server !? "value").asInstanceOf[Int]
    println("value v2 = "+v2)
    server ! "shutdown"
  }
  
  val server = actor {
    var value: Int = 0
    loop {
      react {
        case v: Int => value = v
        case "value" => reply(value)
        case "shutdown" => exit()
      }
    }
  }
  
}
***/

import scala.actors.Actor
import scala.actors.Actor._

object ClientServer extends Application {

  val server = new Server
  val client = new Client(server)

  client.start
  server.start
}

class Client(server: Actor) extends Actor {

  var v1: Int = _
  var v2: Int = _

  def act() = {
    server ! 5
    v1 = (server !? "value").asInstanceOf[Int]
    println("value v1 = "+v1)
    v2 = (server !? "value").asInstanceOf[Int]
    println("value v2 = "+v2)
    //assert(v1 == v2)
    server ! "shutdown"
  }
} 

class Server extends Actor {

  var value: Int = _

  def act() =  loop {
    react {
      case v: Int => value = v
      case "value" => reply(value)
      case "shutdown" => exit()
    }
  }
}
