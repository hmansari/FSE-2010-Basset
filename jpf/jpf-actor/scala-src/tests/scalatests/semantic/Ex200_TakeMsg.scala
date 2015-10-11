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

import scala.actors.Actor._

/**
 * This is a contrived example that demonstrate how an actor can remove messages 
 * from its mailbox. Theoretically, actors should not have access to their mailbox. 
 * However, the Scala library allows an actor to take a message out of its mailbox 
 * through the `?` operator.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex200_TakeMsg extends Application {

  val a = actor {
    self.react {
      case any => 
        self ! any // requeue the message in the mailbox of `this` actor
        val msg = self.? // take out the last queued message
        println(msg) 
    }
  }

  a ! 'msg

}
