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
import scala.actors.Exit

/**
 * Here we show a more advanced example that uses several operators that we 
 * have introduced in the previous code examples. This should give you a feeling 
 * of how actors can be composed.
 * 
 * Two actors `ping`and `pong` exchange one message and then they send to each 
 * other a <code>Exit</code> messgae which is used to explictly shut down the 
 * actor, so that they get out of the `loop`. 
 * 
 * Note that there is only one possible execution trace.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex16_SynchCall extends Application {

  // An actor that either reply to message 'ping with a 'pong message, or prints 
  // the message in the standard output
  val ping = actor {
    loop {
      self.react {
        case 'ping =>
          //println(self+ " got ping")
          self.reply('pong)
        case Exit =>
          //println(this+ " got Exit")
          self.reply(Exit)
          exit()
        case junk =>
          //println(self+ " got junk "+junk)
          println(junk)
      }
    }
  }

  val pong = actor {
    // synchronous call, the actor stops its computation until it gets a value back
    loop {
      self.react {
        case 'start =>
          //println(self+ " got start")
          val answer = ping !? 'ping
          assert(answer == 'pong) //the received answer must be a 'pong messages
          ping ! Exit
        case Exit =>
          //println(self+ " got Exit")
          exit()
      }
    }
  }

  pong ! 'start

}
