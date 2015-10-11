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

/** !!!!! POR !!!!! */

import scala.actors.Actor._
import scala.actors.{Exit, Actor}

/**
 * The following is an interesting example because it shows that `react` blocks
 * can be composed (look at the <code>logger</code> actor), and we show an interesting 
 * use of the `send` operator, which allows to explictly pass the "customer" of 
 * a message (together with the message itself). 
 * 
 * Here, thanks to the concept of "customer", we have implemented a ping-pong 
 * application where the replies sent by actors <code>ping</code> and <code>pong</code>
 * are sent to a <code>logger</code> actor. Now it should be clear why we decided 
 * to call "customer" the actor that is associated with a sent message.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex20_SendCustomer extends Application {

  val logger: Actor = actor {
    self.react { // first react block
      case log => 
        println(log)
        // stops here and wait for next message to be processed
        self.react { // inner react block
          case log => println(log)
        }
    }
  }

  val ping: Actor = actor {
    self.react {
      case 'ping => 
        // this send the "ack ping" message to the customer of message 'ping 
        // (which is the <code>logger</code> actor)
        self.reply("ack ping") 
        pong send ('pong,logger)
    }
  }

  val pong: Actor = actor {
    self.react {
      case 'pong => 
        // this send the "ack ping" message to the customer of message 'ping 
        // (which is the <code>logger</code> actor)
        self.reply("ack pong")
    }
  }

  // pass the <code>logger</code> as "customer" of message 'ping
  ping send ('ping,logger)

}
