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
 * This example is meant to shown the semantic of `exit`, which it never returns 
 * normally (as explained in example <code>Ex13_Loop</code>. Hence, no exception 
 * will be thrown when this program is ran.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex14_Loop extends Application {

  val a = actor {
    loop { // create a loop, what will be defined next is the behavior of the actor
      self.react { // usual `react` call, but since it's inside a `loop` this will be repeated for all messages
        case Exit => {  
          self.exit()  // library method to terminate the actor. This actor won't process any more messages
          // Anything that follows an `exit` call is dead code (though, no compiler warning is issued)
          throw new Exception("Never thrown")
        }
        case x => 
          println(x)
          self ! Exit  // send an Exit message to itself
      }
    }
  }

  a ! 'msg

}

