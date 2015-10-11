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
package scalaexamples.fibonacci

import gov.nasa.jpf.actor.env.TestDriver

import scala.actors.{Actor,OutputChannel}
import scala.actors.Actor._ 

import scalaexamples.util.Main

object Fibo extends TestDriver with Main {
  
  def default() = {
    input = 4
    expected = 3 
  }
  
  def mainBody(): Unit = { 
    val fib = new Fibonacci
    val res = (fib !? Fib(input.get)).asInstanceOf[Result]
    if(expected.isDefined){
      assert( res == Result(expected.get), this.getClass+": Expected result for Fib("+input+") is "+expected.get+", not "+res.v);
    }
  }
}


case class Fib(value: Int)
case class Result(v: Int)

class Fibonacci extends Actor {
  start
  
  var contr: Boolean = _
  
  
  private var counter = 2
  private var result = 0
  private var replyDst: OutputChannel[Any] = _
  
  
  def act() = loop { react {
    case Fib(0) =>
      reply(Result(0))
    case Fib(n) if (n < 3) =>
      reply(Result(1))
    case Fib(n) => 
      if(replyDst eq null)
        replyDst = sender
      
      val newChild = new Fibonacci()
      newChild ! Fib(n-1)
      newChild ! Fib(n-2)
      
      
      
    case Result(n) =>
      counter = counter - 1
      result = result + n
      if(counter == 0) {
        replyDst ! Result(result)
        counter = 2
        result = 0
      }
  }}
}