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
 * This class contains a series of JUnit tests that exercise various semantic
 * features of the ActorFoundry instantiation of Basset.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class FoundrySemanticTest extends TestBassetJPF {

  static final String LISTENER = "+listener=.actor.BassetListener";

  public static void main(String[] args) {
    runTestsOfThisClass(args);
  }

  // --- the test methods

  @Test
  public void test1() {
    String subjectClass = "gov.nasa.jpf.test.actor.foundrytests.test1.Driver";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(4, 0, 0, 15, 4, 0, 0, 0);
  }

  @Test
  public void test2() {
    String subjectClass = "gov.nasa.jpf.test.actor.foundrytests.test2.Driver";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(4, 0, 0, 15, 4, 0, 0, 0);
  }

  @Test
  public void test3() {
    String subjectClass = "gov.nasa.jpf.test.actor.foundrytests.test3.Driver";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(3, 1, 0, 8, 3, 3, 0, 0);
  }

  @Test
  public void test4() {
    String subjectClass = "gov.nasa.jpf.test.actor.foundrytests.test4.Driver";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(6, 0, 0, 20, 5, 0, 0, 0);
  }

}
