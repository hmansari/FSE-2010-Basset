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
 * Test case for testing linearization and copying of objects with enum fields
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjEnum extends BaseTestObject {

  public static String expected = "(5001,6001,5002,6002,5003,6003,5004,6004,11,73,110,116,114,111,32,116,111,32,67,83,0,11,0,300,5005,6003,5006,6004,5,67,83,49,50,53,0,5,0,0,5007,6002,5008,6003,5009,6004,16,77,97,99,104,105,110,101,32,76,101,97,114,110,105,110,103,0,16,0,20,5010,6003,5011,6004,5,67,83,53,52,54,0,5,0,4)";

  public enum CSclass {
    CS125("Intro to CS", 300), CS242("Programming Studio", 200), CS357(
        "Numerical Methods", 200), CS473("Algorithms", 100), CS546(
        "Machine Learning", 20);

    // having a field called "name" in an enum makes copy fail...
    // maybe has something to do with how java creates/stores enums
    // public final String name;
    public final String naMe;
    public final int students;

    CSclass(String n, int s) {
      // name = n; arbitrary
      naMe = n;
      students = s;
    }
  }

  private CSclass c1;
  private CSclass c2;

  public ObjEnum() {
    c1 = CSclass.CS125;
    c2 = CSclass.CS546;
  }

  public static void main(String[] args) {
    original = new ObjEnum();
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
