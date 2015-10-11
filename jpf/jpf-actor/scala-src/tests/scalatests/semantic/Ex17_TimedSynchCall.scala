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
package scalatests.semantic

/*
 * !!!Partial Order Reduction TEST!!
 * XXX: In this example "pong" is printed twice. The reason is that when `!?` is 
 * called with a timeout, a TIMEOUT message is enqueued together with the sent "pong".
 * Hence we have two behaviors: either send message "pong" and then TIMEOUT or 
 * TIMEOUT and then "pong". By implementing a POR that consider the fact that the 
 * two messages are not releated, we would be able to prune one of the two explorations ...
 */

import scala.actors.Actor._

/**
 * In this example we show how to use a synchronous call with a timeout, this 
 * ensure that the caller actor doesn't block forever in case the calle don't 
 * (or takes too much time ) to reply.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex17_TimedSynchCall extends Application {

  val pong = actor {
    self.react {
      case 'pong => println("pong") // don't reply to 'pong message
    }
  }

  val ping = actor {
    // synchronous call with a timeout of 10ms
    val answer = pong !? (10,'pong)
    assert (answer == None) //no reply => answer is None
  }

}
