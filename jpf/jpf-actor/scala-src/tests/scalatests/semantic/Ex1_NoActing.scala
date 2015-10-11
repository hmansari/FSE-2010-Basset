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

import scala.actors.Actor.actor // import the function 'actor'  

/**
 * This is probably the simplest actor that you will ever create. It has 
 * almost no behavior at all, but it still represents an interesting example 
 * since it shows how you can create an actor in Scala.
 * 
 * Note that in Scala the keyword <code>object</code> is used to declare a 
 * singleton object. Finally, we extends trait <code>Application</code> so that 
 * the body of object <code>Ex1_NoActing</code> will be used as the <code>main
 * </code> definition. 
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex1_NoActing extends Application {

  // create an actor and implicitly start it
  actor {
    println("acting") // defines its behavior (in this case simply print 'acting'
  }

}
