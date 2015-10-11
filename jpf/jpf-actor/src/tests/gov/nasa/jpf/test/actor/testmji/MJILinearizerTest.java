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
import java.util.List;
import gov.nasa.jpf.JPF;
import org.junit.Test;
import static org.junit.Assert.*;
import static gov.nasa.jpf.test.actor.testmji.JPF_gov_nasa_jpf_test_actor_testmji_MJIUtil.linArray;

/**
 * This class contains a series of JUnit tests for the MJILinearizer class.
 * 
 * @author Bobak Hadidi (bhadidi2@illinois.edu)
 * 
 */
public class MJILinearizerTest {

  /***********************************************************/
  protected void run(String driverClass) {
    LinkedList<String> jpfArgs = new LinkedList<String>();
    jpfArgs.add(driverClass);
    String[] args = jpfArgs.toArray(new String[0]);
    JPF jpf = new JPF(args);
    jpf.run();
  }

  /***********************************************************/
  public static StringBuffer makeStringBuf(List<Integer> linearization) {
    StringBuffer buf = new StringBuffer();
    buf.append("(");

    if (linearization == null)
      System.out.println("is null");

    int i = 0;
    for (; i < linearization.size() - 1; i++) {
      buf.append(linearization.get(i));
      buf.append(",");
    }
    if (linearization.size() != 0)
      buf.append(linearization.get(i));
    buf.append(")");
    return buf;
  }

  /*
   * Tests 1. ObjSimple 2. ObjPrimitive 3. ObjObjs 4. ObjCycle 5. ObjSubclass 6.
   * ObjEnumSimple 7. ObjEnum 8. ObjPrimArray 9. ObjMultiPrimArray 10.
   * ObjObjArray 11. ObjMultiObjArray 12. ObjRefArrSize0 13. ObjStatic 14.
   * ObjDataStructs 15. ObjDataStructs2 16. ObjDataStructs3
   */

  // 1. Object with int and null ref
  @Test
  public void TestSimpleObj() {
    run(gov.nasa.jpf.test.actor.testmji.ObjSimple.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjSimple.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for robustness
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 2. Object with only primitive fields
  @Test
  public void TestObjPrimitives() {
    run(gov.nasa.jpf.test.actor.testmji.ObjPrimitives.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjPrimitives.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 3. Object with other object references
  @Test
  public void TestObjObjs() {
    run(gov.nasa.jpf.test.actor.testmji.ObjObjs.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjObjs.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 4. Object with circular references (cyclic graph)
  @Test
  public void TestCycle() {
    run(gov.nasa.jpf.test.actor.testmji.ObjCycle.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjCycle.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 5. Object extending another class with new fields
  @Test
  public void TestSubClass() {
    run(gov.nasa.jpf.test.actor.testmji.ObjSubclass.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjSubclass.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 6. Object with simple enum fields
  @Test
  public void TestEnumSimple() {
    run(gov.nasa.jpf.test.actor.testmji.ObjEnumSimple.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    System.out.println(result);
    assertEquals(ObjEnumSimple.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 7. Object with enum fields
  @Test
  public void TestEnum() {
    run(gov.nasa.jpf.test.actor.testmji.ObjEnum.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    System.out.println(result);
    assertEquals(ObjEnum.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 8. Object with primitive arrays
  @Test
  public void TestPrimArray() {
    run(gov.nasa.jpf.test.actor.testmji.ObjPrimArray.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjPrimArray.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 9. Object with multidimensional primitive arrays
  @Test
  public void TestMultiPrimArray() {
    run(gov.nasa.jpf.test.actor.testmji.ObjMultiPrimArray.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjMultiPrimArray.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 10. Object with object arrays
  @Test
  public void TestObjArray() {
    run(gov.nasa.jpf.test.actor.testmji.ObjObjArray.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjObjArray.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 11. Object with multidimensional object arrays
  @Test
  public void TestMultiObjArray() {
    run(gov.nasa.jpf.test.actor.testmji.ObjMultiObjArray.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    assertEquals(ObjMultiObjArray.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 12. Object with a reference array of size 0
  @Test
  public void TestRefArraySize0() {
    run(gov.nasa.jpf.test.actor.testmji.ObjRefArrSize0.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    System.out.println(result);
    assertEquals(ObjRefArrSize0.expected, result.toString());

    // test the delinearization
  }

  // 13. Object implementing an interface with (static) fields
  @Test
  public void TestStatic() {
    run(gov.nasa.jpf.test.actor.testmji.ObjStatic.class.getName());
    String result = makeStringBuf(linArray[0]).toString();
    // System.out.println(result);
    // Static fields not implemented, just ObjId and ClassInfo
    assertEquals(ObjStatic.expected, result.toString());

    // now test the delinearization
    // delinearize, then relinearizes, compare the initial and second
    // linearizations
    // alternatively, could implement equals() and hashCode() for each test
    // object
    // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
    // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
    // assertEquals(expected,
    // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  }

  // 14. Object with some common data structure references - TODO: fix this test
  // @Test
  // public void TestDataStructs() {
  // run(gov.nasa.jpf.test.actor.testmji.ObjDataStructs.class.getName());
  // String result = makeStringBuf(linArray[0]).toString();
  // // System.out.println(result);
  // assertEquals(ObjDataStructs.expected, result.toString());
  //
  // // now test the delinearization
  // // delinearize, then relinearizes, compare the initial and second
  // // linearizations
  // // alternatively, could implement equals() and hashCode() for each test
  // // object
  // //
  // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
  // // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
  // // assertEquals(expected,
  // //
  // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  // }

  // 15. Object with some other common data structure references - TODO: fix this test
  // @Test
  // public void TestDataStructs2() {
  // run(gov.nasa.jpf.test.actor.testmji.ObjDataStructs2.class.getName());
  // String result = makeStringBuf(linArray[0]).toString();
  // // System.out.println(result);
  // assertEquals(ObjDataStructs2.expected, result.toString());
  //
  // // now test the delinearization
  // // delinearize, then relinearizes, compare the initial and second
  // // linearizations
  // // alternatively, could implement equals() and hashCode() for each test
  // // object
  // //
  // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
  // // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
  // // assertEquals(expected,
  // //
  // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  // }

  // 16. Object with some other common data structure references - TODO: fix this test
  // @Test
  // public void TestDataStructs3() {
  // run(gov.nasa.jpf.test.actor.testmji.ObjDataStructs3.class.getName());
  // String result = makeStringBuf(linArray[0]).toString();
  // // System.out.println(result);
  // assertEquals(ObjDataStructs3.expected, result.toString());
  //
  // // now test the delinearization
  // // delinearize, then relinearizes, compares the initial and second
  // // linearizations
  // // alternatively, could implement equals() and hashCode() for each test
  // // object
  // //
  // assertEquals(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization.toString(),
  // // JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2.toString());
  // // assertEquals(expected,
  // //
  // makeString(JPF_gov_nasa_jpf_actor_testmji_TestLinearize.linearization2).toString());
  // }

}
