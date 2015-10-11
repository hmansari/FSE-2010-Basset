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

// The Exit message is just a convienent library message that can is generally used  
// for handling the set of action needed before terminating the actor execution
import scala.actors.Exit 
import scala.actors.Actor._

/**
 * In this example we show how define an actor that stays alive after processing 
 * the first message. In fact, in all examples so far, the defined actors were able 
 * to process one message and then they were silently stopping their execution. 
 * Now we want to create an actor that process any number of message and stays alive 
 * if no message is in the mailbox. We will be able to do that by using `loop`. 
 * Moreover, in order to kill an actor, we introduce the `exit` operator, which 
 * is intended to be used to terminate the execution of the actor.
 * 
 * Note that `exit` has the particularity that it never returns normally (just as
 * the `react` operator) and an exception is thrown instead. Hence, any code 
 * AFTER `exit`will NEVER be execute. Look at the next example <code>Ex14_Loop</code>
 * for an example of this behavior.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex13_Loop extends Application {

  val a = actor {
    loop { // create a loop, what will be defined next is the behavior of the actor
      self.react { // usual `react` call, but since it's inside a `loop` this will be repeated for all messages
        case Exit => // Code executed when an `Exit` message is received 
          self.exit()  // library method to terminate the actor. This actor won't process any more messages
        case x => 
          println(x)
          self ! Exit  // send an Exit message to itself
      }
    }
  }

  a ! 'msg

}
