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
package gov.nasa.jpf.test.actor;

import org.junit.Test;

/**
 * This class contains a series of JUnit tests that correctly result in
 * deadlocks for the Scala instantiation of Basset.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class ScalaDeadlockTest extends TestBassetJPF {

  static final String LISTENER = "+listener=.actor.BassetListener";

  public static void main(String[] args) {
    runTestsOfThisClass(args);
  }

  // --- the test methods

  @Test
  public void ex0() {
    String subjectClass = "scalatests.deadlock.Ex0_TwoActorsDeadlocked";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 1, 0, 0, 3, 0, 1, 1);
  }

  @Test
  public void ex1() {
    String subjectClass = "scalatests.deadlock.Ex1_Classic";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 1, 0, 2, 4, 0, 1, 1);
  }

  @Test
  public void ex2() {
    String subjectClass = "scalatests.deadlock.Ex2_ForgottenReply";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 3, 0, 2, 1);
  }

}
