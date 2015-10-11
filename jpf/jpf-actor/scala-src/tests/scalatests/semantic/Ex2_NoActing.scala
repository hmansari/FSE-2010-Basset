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

import scala.actors.Actor // import the Actor trait  

/**
 * An actor can also be created by extending the Scala's trait <code>Actor</code>. 
 * The following program is behaviorally equivalent to <code>Ex1_NoActing</code> 
 * but there is a little more code to write (e.g the actor shall be explicitly
 * started).
 * 
 * Note, since we extends trait <code>Actor</code>, the abstract method <code>act
 * </code> need to be implemented. This will contain the initial behavior of <code> 
 * this</code> actor. 
 * In the next examples we will prefer the first form, e.g.: 
 *
 *     actor {
 *       //behavior
 *     }
 * 
 * over the more cumbersome "extending the Actor trait" one, presented here. This 
 * is only for conciseness.
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex2_NoActing extends Actor with Application{

  // defines the initial behavior of the actor
  def act() = println("acting")

  // explictly start <code>this</code> actor
  this.start

}

