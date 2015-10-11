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
 * In this example we introduce two important, releated, operations. First, we 
 * have a synchronous call `!?` operation, so that the caller get blocked until 
 * the callee replies to his message, which is done by using `reply`. 
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex11_SynchCall extends Application {

  // An actor that either reply to message 'ping with a 'pong message, or prints 
  // the message in the standard output
  val ping = actor {
    self.react {
      case 'ping => self.reply('pong)
      case junk => println(junk)
    }
  }

  val pong = actor {
    // synchronous call, the actor stops its computation until it gets a value back
    val answer = ping !? 'ping
    assert(answer == 'pong) //the received answer must be a 'pong messages
  }

}
