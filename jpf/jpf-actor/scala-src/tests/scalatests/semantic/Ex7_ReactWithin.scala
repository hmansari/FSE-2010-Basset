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
import scala.actors.{TIMEOUT} // Import TIMEOUT message

/**
 * In this example we show how an actor can be forced to take an action if 
 * no message is sent to it within a given time bound. 
 * The code should be self-explanatory and the new concepts are introduced in 
 * comment. 
 * 
 * We assume that you afre confortable with some of the basic Scala language 
 * constructs e.g. pattern matching, casse classes, and so on. If this is not the 
 * case we invite you to take a look at this gentle introduction to Scala 
 * (http://www.scala-lang.org/node/104).
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex7_ReactWithin extends Application {

  actor {
    //same semantic as `react`, but force the actor to take some action within 10 milliseconds
    self.reactWithin(10) { 
      case TIMEOUT => println("timeout occurred") // if a TIMEOUT message is received
      case any => println(any) 
    }
  }

}
