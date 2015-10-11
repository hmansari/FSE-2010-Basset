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
 * ActorFoundry instantiation of Basset.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class FoundryShortRunTest extends TestBassetJPF {

  static final String LISTENER = "+listener=.actor.BassetListener";

  public static void main(String[] args) {
    runTestsOfThisClass(args);
  }

  // --- the test methods

  @Test
  public void fibonacci() {
    Class<?> subjectClass = fibonacci.Driver.class;
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "3" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(6, 0, 0, 27, 5, 0, 0, 0);
  }

  @Test
  public void pi() {
    Class<?> subjectClass = pi.Driver.class;
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "2" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(12, 0, 0, 44, 4, 30, 0, 0);
  }

  @Test
  public void pipesort() {
    Class<?> subjectClass = pipesort.Driver.class;
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "2" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(11, 0, 0, 38, 11, 0, 0, 0);
  }

  // @Test
  // public void shortestpath() {
  // Class<?> subjectClass = shortestpath.Driver.class;
  // String[] options = new String[] { LISTENER };
  // String[] subjectArgs = new String[] { "4" };
  //
  // runBasset("foundry", options, subjectClass, subjectArgs);
  // checkResults(3614, 0, 0, 9999, 5, 0, 0, 0);
  // }

  @Test
  public void server() {
    Class<?> subjectClass = server.Driver.class;
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(6, 1, 0, 27, 3, 6, 0, 0);
  }

  @Test
  public void mergesort() {
    Class<?> subjectClass = mergesort.Driver.class;
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "3" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(168, 0, 0, 601, 10, 0, 0, 0);
  }

  @Test
  public void quicksort() {
    Class<?> subjectClass = quicksort.Driver.class;
    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] { "3" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(168, 0, 0, 601, 11, 0, 0, 0);
  }

}
