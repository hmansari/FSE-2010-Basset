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
public class FoundryShortDPORTest extends TestBassetJPF {

  static final String LISTENER = "+listener=.actor.BassetListener";

  public static void main(String[] args) {
    runTestsOfThisClass(args);
  }

  // --- the test methods

  @Test
  public void fib5_dcute_lca() {
    Class<?> subjectClass = fibonacci.Driver.class;
    String[] options = new String[] { LISTENER, "+basset.dpor=1", "+basset.dpor_heuristic=2" };
    String[] subjectArgs = new String[] { "5" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(16, 0, 0, 92, 15, 0, 0, 0);
  }
  
  @Test
  public void fib5_pset_lca() {
    Class<?> subjectClass = fibonacci.Driver.class;
    String[] options = new String[] { LISTENER, "+basset.dpor=3", "+basset.dpor_heuristic=2" };
    String[] subjectArgs = new String[] { "5" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(16, 0, 0, 92, 15, 0, 0, 0);
  }
  
  @Test
  public void fib5_dcute_fifo() {
    Class<?> subjectClass = fibonacci.Driver.class;
    String[] options = new String[] { LISTENER, "+basset.dpor=1", "+basset.dpor_heuristic=3" };
    String[] subjectArgs = new String[] { "5" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(68, 0, 0, 322, 11, 0, 0, 0);
  }
  
  @Test
  public void fib5_pset_fifo() {
    Class<?> subjectClass = fibonacci.Driver.class;
    String[] options = new String[] { LISTENER, "+basset.dpor=3", "+basset.dpor_heuristic=3" };
    String[] subjectArgs = new String[] { "5" };

    runBasset("foundry", options, subjectClass, subjectArgs);
    checkResults(40, 0, 0, 204, 11, 0, 0, 0);
  }
  
}
