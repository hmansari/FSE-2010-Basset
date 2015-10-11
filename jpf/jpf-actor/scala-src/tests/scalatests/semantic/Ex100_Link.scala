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
import scala.actors.Exit

/**
 * Here we link a <code>master</code> actor to a <code>slave</code> one. Then 
 * we send an <code>Exit</code> message to the master which will be exiting 
 * with message 'shutdown. Since the exiting message is not "'normal", the linked 
 * slave actors is forced to stop its execution as well (note that if we had used 
 * <code>exit()</code> instead of <code>exit('severe)</code> then the <code>slave
 * </code> actor would have not been forced to stop it's execution.
 * 
 * In the next example we show how implement this behavior without passing an
 * exiting message to exit`.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex100_Link extends Application {

  val master = actor {
    link(slave)

    loop {
      self.react {
        case Exit =>
          exit('shutdown) //'normal has special meaning in the library
      }
    }
  }

  val slave = actor {
    loop {
      react {
        case any => println(any)
      }
    }
  }

  master ! Exit

}
