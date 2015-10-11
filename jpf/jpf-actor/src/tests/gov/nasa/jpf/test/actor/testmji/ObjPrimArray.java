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
 * Test case for testing linearization and copying of objects with primitive
 * arrays
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjPrimArray extends BaseTestObject {

  public static String expected = "(5001,6001,5002,6002,2,-1,-2,5003,6003,5,1,0,1,0,1,5004,6004,3,10,20,30)";

  private int[] arrint1;
  private boolean[] arrbool1;
  private byte[] arrbyte1;

  public ObjPrimArray() {
    // array of primitives
    arrint1 = new int[2];
    arrint1[0] = -1;
    arrint1[1] = -2;
    arrbool1 = new boolean[5];
    arrbool1[0] = true;
    arrbool1[1] = false;
    arrbool1[2] = true;
    arrbool1[3] = false;
    arrbool1[4] = true;
    arrbyte1 = new byte[3];
    arrbyte1[0] = 10;
    arrbyte1[1] = 20;
    arrbyte1[2] = 30;
  }

  public static void main(String[] args) {
    original = new ObjPrimArray();
    if (args.length == 0) {
      MJIUtil.linearize(original, 0);
      // delinearized = TestLinearize.delinearize(original);
    } else {
      MJIUtil.linearize(original, 0);
      Object copy = MJIUtil.copy(original);
      MJIUtil.linearize(copy, 1);
    }
  }
}
