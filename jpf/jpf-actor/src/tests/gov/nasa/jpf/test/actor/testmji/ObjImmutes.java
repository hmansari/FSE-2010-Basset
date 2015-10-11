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

import osl.manager.ActorName;

/**
 * Test case for testing linearization and copying of objects with immutable
 * object references
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class ObjImmutes extends ObjObjs {

  // from the super class - we are interested in string1
  public ActorName an;

  public ObjImmutes() {
    super();
    an = new ActorName();
  }

  public static void main(String[] args) {
    original = new ObjImmutes();
    Object copy = MJIUtil.copy(original);

    // make sure the string obj refs are the same
    if (((ObjImmutes) original).string1 == ((ObjImmutes) copy).string1)
      // store the result of the first assert where TestCopy can find it
      MJIUtil.linearize(null, 0);
    else
      MJIUtil.linearize(new Integer(100), 0); // failure

    // if one object changes, the other still should not
    ((ObjImmutes) original).an = new ActorName();
    if (!((ObjImmutes) copy).an.getName().equals(
        (((ObjImmutes) original).an.getName())))
      MJIUtil.linearize(null, 1);
    else
      MJIUtil.linearize(new Integer(101), 1); // failure

    // sanity check
    if (!(((ObjImmutes) original).os1 == ((ObjImmutes) copy).os1))
      MJIUtil.linearize(null, 2);
    else
      MJIUtil.linearize(new Integer(102), 2); // failure
  }

}
