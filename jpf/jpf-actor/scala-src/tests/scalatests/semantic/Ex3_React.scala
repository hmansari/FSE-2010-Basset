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

import scala.actors.Actor._ // import all the public definition of the Actor object

/**
 * In this example we introduce two of the most important operator for building 
 * actors application: `!` and `react`. Method `!` is used to send a message 
 * (asynchronously) to an actor. On the other hand, `react` is used for defining 
 * the behavior of the actor when a message is received. 
 * 
 * Note that actor `a` will finish its computation after having printed the message.
 * We will see in example <code>Ex13_Loop</code> how we can keep the actor alive for 
 * processing subsequent incoming messages.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex3_React extends Application {

  // definition of an actor `a`
  val a = actor {
    self.react { // receiving a message is an explicit operation 
      case any => println(any)  // for <code>any</code> received message, just print it
    }
  }

  a ! 'msg // send message `'msg` to actor `a`

}

