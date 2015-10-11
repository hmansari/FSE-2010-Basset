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
 * Test case for testing linearization and copying of objects with object arrays
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjMultiObjArray extends BaseTestObject {

  public static String expected = "(5001,6001,5002,6002,2,5003,6003,2,5004,6004,10,5005,6004,11,5006,6003,2,5007,6004,12,5008,6004,13,5009,6005,2,5010,6006,3,5011,6007,1,5012,6007,1,5013,6007,1,5014,6006,3,5015,6007,0,5016,6007,0,5017,6007,0)";

  private Short[][] arrSt;
  private Boolean[][] arrBl;

  public ObjMultiObjArray() {
    arrSt = new Short[2][2];
    arrSt[0][0] = new Short((short) 10);
    arrSt[0][1] = new Short((short) 11);
    arrSt[1][0] = new Short((short) 12);
    arrSt[1][1] = new Short((short) 13);
    arrBl = new Boolean[2][3];
    arrBl[0][0] = new Boolean(true);
    arrBl[0][1] = new Boolean(true);
    arrBl[0][2] = new Boolean(true);
    arrBl[1][0] = new Boolean(false);
    arrBl[1][1] = new Boolean(false);
    arrBl[1][2] = new Boolean(false);
  }

  public static void main(String[] args) {
    original = new ObjMultiObjArray();
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
