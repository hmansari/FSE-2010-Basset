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
 * features of the Scala instantiation of Basset.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class ScalaSemanticTest extends TestBassetJPF {

  static final String LISTENER = "+listener=.actor.BassetListener";

  public static void main(String[] args) {
    runTestsOfThisClass(args);
  }

  // --- the test methods

  @Test
  public void ex1() {
    String subjectClass = "scalatests.semantic.Ex1_NoActing";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 0, 2, 0, 2, 0);
  }

  @Test
  public void ex2() {
    String subjectClass = "scalatests.semantic.Ex2_NoActing";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 0, 2, 0, 2, 0);
  }

  @Test
  public void ex3() {
    String subjectClass = "scalatests.semantic.Ex3_React";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex4() {
    String subjectClass = "scalatests.semantic.Ex4_React";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex5() {
    String subjectClass = "scalatests.semantic.Ex5_Receive";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex6() {
    String subjectClass = "scalatests.semantic.Ex6_Receive";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex7() {
    String subjectClass = "scalatests.semantic.Ex7_ReactWithin";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex8() {
    String subjectClass = "scalatests.semantic.Ex8_ReactWithin";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex9() {
    String subjectClass = "scalatests.semantic.Ex9_ReceiveWithin";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex10() {
    String subjectClass = "scalatests.semantic.Ex10_ReceiveWithin";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex11() {
    String subjectClass = "scalatests.semantic.Ex11_SynchCall";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 3, 0, 3, 0);
  }

  @Test
  public void ex12() {
    String subjectClass = "scalatests.semantic.Ex12_Forward";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 3, 0, 3, 0);
  }

  @Test
  public void ex13() {
    String subjectClass = "scalatests.semantic.Ex13_Loop";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 2, 0, 2, 0);
  }

  @Test
  public void ex14() {
    String subjectClass = "scalatests.semantic.Ex14_Loop";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 2, 0, 2, 0);
  }

  @Test
  public void ex15() {
    String subjectClass = "scalatests.semantic.Ex15_Loop";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 2, 0, 2, 0);
  }

  @Test
  public void ex16() {
    String subjectClass = "scalatests.semantic.Ex16_SynchCall";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 5, 3, 0, 3, 0);
  }

  @Test
  public void ex17() {
    String subjectClass = "scalatests.semantic.Ex17_TimedSynchCall";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(2, 0, 0, 4, 3, 0, 5, 0);
  }

  @Test
  public void ex18() {
    String subjectClass = "scalatests.semantic.Ex18_SendBang";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex19() {
    String subjectClass = "scalatests.semantic.Ex19_SendForward";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 3, 0, 3, 0);
  }

  @Test
  public void ex20() {
    String subjectClass = "scalatests.semantic.Ex20_SendCustomer";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(3, 0, 0, 9, 4, 0, 7, 0);
  }

  @Test
  public void ex21() {
    String subjectClass = "scalatests.semantic.Ex21_Receiver";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 2, 0, 2, 0);
  }

  @Test
  public void ex22() {
    String subjectClass = "scalatests.semantic.Ex22_LoopWhile";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 2, 0, 2, 0);
  }

  @Test
  public void ex100() {
    String subjectClass = "scalatests.semantic.Ex100_Link";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 1, 3, 0, 3, 0);
  }

  @Test
  public void ex101() {
    String subjectClass = "scalatests.semantic.Ex101_LinkTrapExit";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 3, 0, 3, 0);
  }

  @Test
  public void ex200() {
    String subjectClass = "scalatests.semantic.Ex200_TakeMsg";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 2, 2, 0, 2, 0);
  }

  @Test
  public void ex201() {
    String subjectClass = "scalatests.semantic.Ex201_MailboxSize";

    String[] options = new String[] { LISTENER };
    String[] subjectArgs = new String[] {};
    runBasset("scala", options, subjectClass, subjectArgs);
    checkResults(1, 0, 0, 0, 2, 0, 2, 0);
  }

}
