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
 * In this example we whow how similar 'react' and 'receive' look like from the 
 * user perspective. As a matter of fact, this example shows the same behavior 
 * of example <code>Ex3_React</code>. However, as introduced when example <code>
 * Ex4_React</code> has been discussed, by using `receive`the actor is attached 
 * to a thread for all its lifecycle. We will see in the next example <code>
 * Ex6_Receive</code> the only semantic difference between `react`and `receive`.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex5_Receive extends Application {

  val a = actor {
    self.receive { //using `receive` instead of `react`
      case any => println(any)
    }
  } 

  a ! 'msg

}
