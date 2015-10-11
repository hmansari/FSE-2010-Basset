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
 * Test case for testing linearization and copying of objects with
 * multidimensional primitive arrays
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjMultiPrimArray extends BaseTestObject {

  public static String expected = "(5001,6001,5002,6002,2,5003,6003,2,0,1,5004,6003,2,2,3,5005,6004,2,5006,6005,3,97,98,99,5007,6005,3,100,101,102)";

  public int[][] arrarri;
  public char[][] arrarrc;

  public ObjMultiPrimArray() {
    arrarri = new int[2][2];
    arrarri[0][0] = 0;
    arrarri[0][1] = 1;
    arrarri[1][0] = 2;
    arrarri[1][1] = 3;
    arrarrc = new char[2][3];
    arrarrc[0][0] = 'a';
    arrarrc[0][1] = 'b';
    arrarrc[0][2] = 'c';
    arrarrc[1][0] = 'd';
    arrarrc[1][1] = 'e';
    arrarrc[1][2] = 'f';
  }

  public static void main(String[] args) {
    original = new ObjMultiPrimArray();
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
