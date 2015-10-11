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
package scala.actors.jpf

import java.lang.reflect.Method

import gov.nasa.jpf.actor.IUserDriverStarter

import scala.actors.Actor

/**
 * The main class for running Scala examples under the Basset extension
 * for Java PathFinder (JPF).  See http://mir.cs.illinois.edu/basset
 *
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
class ScalaProgramExecutor extends IUserDriverStarter {
  override def exec(mainMethod: Method, mainArgs: Array[Object]) {
    new MainProgramActor(mainMethod, mainArgs).start
  }
}

private[jpf] class MainProgramActor(mainMethod: Method, mainArgs: Array[Object]) extends Actor {
  def act() = mainMethod.invoke(null, mainArgs: _*)
  override def toString() = "Main"
}
