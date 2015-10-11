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
 * This class contains a series of reasonably short running JUnit tests for the
 * Scala instantiation of Basset.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class ScalaShortRunTest extends TestBassetJPF {

  static final String LISTENER = "+listener=.actor.BassetListener";

  public static void main(String[] args) {
    runTestsOfThisClass(args);
  }

  // --- the test methods

  @Test
  public void helloworld() {
    String subjectClass = "scalaexamples.HelloWorld";
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};

    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 0, 2, 0, 2, 0);
  }

  @Test
  public void pi() {
    String subjectClass = "scalaexamples.pi.Pi";
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "2" };

    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(12, 0, 0, 42, 4, 0, 31, 0);
  }

  @Test
  public void spinsort() {
    String subjectClass = "scalaexamples.dcute.SpinSort";
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "2" };

    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(2, 0, 0, 6, 4, 0, 1, 0);
  }

  @Test
  public void server() {
    String subjectClass = "scalaexamples.clientserver.ClientServer";
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};

    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(6, 1, 0, 25, 3, 0, 12, 0);
  }

  @Test
  public void mergesort() {
    String subjectClass = "scalaexamples.mergesort.MergeSortCopy";
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "3" };

    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(168, 0, 0, 599, 10, 0, 383, 0);
  }

}
