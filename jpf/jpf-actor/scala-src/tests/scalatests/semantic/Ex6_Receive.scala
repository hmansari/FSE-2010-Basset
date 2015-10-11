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
 * Here we describe the only difference existing between the `react`and `receive` 
 * semantic. We have sadi that `react`never returns normally and hence any code 
 * after a call `react` is as a fact unreachable code. On the other hand, `receive` 
 * always returns normally. hence any sequence of statement after a `receive` call
 * will be executed as expected. 
 * 
 * But you shall remember the performance trade-off that comes from using `react`
 * or `receive`, since in a JVM you can have millions of objects existing at a given 
 * time but a very limitate amount (a few thousands) of threads. Hence, by using 
 * `react` you potentially allow the existance of million of active actors, which 
 * is not possible if `receive` is used instead.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex6_Receive extends Application {

  val a = actor {
    self.receive {
      case any => println(any)
    }

    // `receive` returns normally so what follows will be executed (compare that with Ex4_React)
    println("This message will be prompted")  
  }

  a ! 'msg

}

