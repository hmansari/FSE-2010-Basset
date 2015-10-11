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

/**
 * NOTE: Examples that starts with 100 or greater are a little obscure and require to 
 * have a good understanding of the library. If you don't understand the behavior 
 * you should take a look at the source code of the library for the <code>Actor</code>
 * trait. 
 */

import scala.actors.Actor._
import scala.actors.{Exit,Actor}

/**
 * Same example as <code>Ex100_Link</code> but here we set <code>trapExit</code>
 * to <code>true</code>. In these way when the <code>master</code> actor calls 
 * `exit`, all linked actors with <code>trapExit</code> set to <code>true</code> 
 * automatically send an <code>Exit(from,reason)</code> message to themselves. 
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex101_LinkTrapExit extends Application {

  val master = actor {
    self.trapExit = true
    link(slave)

    loop {
      react {
        //exit the master and the linked actors (with trapExit set to true)
        case Exit => exit() 
      }
    }
  }

  val slave = actor {
    self.trapExit = true

    loop {
      react {
        case Exit(_,_) =>
          println("exiting slave")
          exit() //exiting message automatically sent 
        case any => println(any)
      }
    }
  }

  master ! Exit

}
