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
 * In this example we show the particular semantic of `react`, which is that it 
 * never returns. Hence, any sequence of statement written after a call to `react`
 * will never be executed (meaning its dead - or unreachable - code).
 * 
 * Note that this example has the same behavior as the previous <code>Ex3_React</code>.  
 * 
 * A final remark about `react` is that by using it the user decide not to tigh 
 * the actor to an heavy OS thread. This is an implementation detail that must 
 * be known by clients of the library since it can considerably affect the 
 * overall performance of the system. Scala also provide an alternative `receive` 
 * call which has a very close semantic to the one of `react`, with the important 
 * difference that an actor is attached to an OS Thread for all its lifecycle. 
 * The next example <code>Ex5_Receive</code> will describe how to use `receive`.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex4_React extends Application {

  val a = actor {
    self.react {
      case any => println(any)
    }
    // Anything that follows a `react` call is dead code (though, no compiler warning is issued)
    throw new Exception("Never thrown") 
  }

  a ! 'msg

}

