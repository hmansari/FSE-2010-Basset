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
package gov.nasa.jpf.test.actor.testmji;

/**
 * Test case for testing linearization and copying of objects with only
 * primitive fields
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjPrimitives extends BaseTestObject {

  // the expected linearization
  public static String expected = "(5001,6001,65,1082972252,687194767,-1,-2139,42)";

  private char char1;
  private double d1;
  private long l1;
  private short s1;

  public ObjPrimitives() {
    char1 = 'A';
    d1 = 923.045;
    l1 = -2139;
    s1 = 42;
  }

  public static void main(String[] args) {
    original = new ObjPrimitives();
    if (args.length == 0) {
      MJIUtil.linearize(original, 0);
    } else {
      MJIUtil.linearize(original, 0);
      Object copy = MJIUtil.copy(original);
      MJIUtil.linearize(copy, 1);
    }
  }
}
