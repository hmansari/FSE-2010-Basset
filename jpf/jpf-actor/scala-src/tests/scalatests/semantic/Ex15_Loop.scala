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

import scala.actors.Exit 
import scala.actors.Actor._

/**
 * In this example we shown that the semantic of `react` stays the same even when 
 * a call to `react` is made whithin a `loop`. Hence, any sequence of statement 
 * after a `react` is, as usual, dead code and won't be executed. 
 * 
 * This example is meant to be a remind of the particular semantic in use for 
 * `react`, which stays the same even if we are the impression that we are defining 
 * a looping block (through `loop`). This execution of this example could look 
 * a little counter-intuitive at a first glance, but it makes perfect sense considering 
 * how `react` is defined. If you still have doubts, please look at the Scala 
 * book 'Programming in Scala' for a more detailed description of actors.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex15_Loop extends Application {

  val a = actor {
    loop { // create a loop, what will be defined next is the behavior of the actor
      self.react { // usual `react` call, but since it's inside a `loop` this will be repeated for all messages
        case Exit => {  
          self.exit()  // library method to terminate the actor. This actor won't process any more messages
        }
        case x => 
          println(x)
          self ! Exit  // send an Exit message to itself
      }

      // Anything that follows a `react` call is dead code (though, no compiler warning is issued)
      throw new Exception("Never thrown")
    }
  }

  a ! 'msg

}
