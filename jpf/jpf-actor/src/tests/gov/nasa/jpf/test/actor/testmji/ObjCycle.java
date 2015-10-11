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
 * Test case for testing linearization and copying of objects with graph-like
 * structures and repeat references
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjCycle extends BaseTestObject {

  // the expected linearization
  public static String expected = "(5001,6001,5002,6002,5003,6002,-5002,5004,6002,-999,-999,-999,-5001,5005,6002,-5004,-999,-999,-5001,-5001,-5004,-999,-5001)";

  public class Node {
    public Node ref1;
    public Node ref2;
    public Node ref3;

    public Node() {
      ref1 = ref2 = ref3 = null;
    }

    public Node(Node o1, Node o2, Node o3) {
      ref1 = o1;
      ref2 = o2;
      ref3 = o3;
    }
  }

  public Node top;

  public ObjCycle() {
    Node o1, o2, o3;
    o2 = new Node();
    o3 = new Node(o2, null, null);
    top = new Node(null, o2, null);
    o1 = new Node(top, o2, o3);
    top.ref1 = o1;
  }

  public static void main(String[] args) {
    original = new ObjCycle();
    // for testing linearization
    if (args.length == 0) {
      MJIUtil.linearize(original, 0);
      // delinearized = TestLinearize.delinearize(original);
    }
    // for testing copy
    else {
      MJIUtil.linearize(original, 0);
      Object copy = MJIUtil.copy(original);
      MJIUtil.linearize(copy, 1);
    }
  }
}
