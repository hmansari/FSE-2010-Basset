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

import java.util.LinkedList;

/**
 * Test case for testing linearization and copying of objects with other object
 * references
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjObjs extends BaseTestObject {

  // the expected linearization
  public static String expected = "(5001,6001,5002,6002,5003,6003,-999,5004,6003,5005,6004,4,5006,6003,5007,6004,1,5008,6003,5009,6004,3,5010,6003,5011,6004,2,-5003,-5008,-5006,-5004,-5003,-5010,4,4,5012,6005,5013,6006,7,116,101,115,116,105,110,103,0,7,0,5014,6007,65,1082972252,687194767,-1,-2139,42,5015,6008,6,-999)";

  private LinkedList<Integer> list1;
  public String string1;
  private ObjPrimitives op1;
  public ObjSimple os1;

  public ObjObjs() {
    list1 = new LinkedList<Integer>();
    list1.add(4);
    list1.add(1);
    list1.add(3);
    list1.add(2);
    string1 = "testing";
    op1 = new ObjPrimitives();
    os1 = new ObjSimple();
  }

  public static void main(String[] args) {
    original = new ObjObjs();
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
