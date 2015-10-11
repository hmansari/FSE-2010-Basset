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
package gov.nasa.jpf.test.actor.foundrytests.test2;

import osl.manager.Actor;
import osl.manager.ActorName;
import osl.manager.RemoteCodeException;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class CCCC extends Actor {

  public CCCC() {
    System.out.println("creating CCCC");
  }

  /*******************************************************/
  public void printValue(ActorName b) throws RemoteCodeException {
    Integer value = (Integer) call(b, "requestValue", self());
    System.out.println("The expected value is 4.  The received value is "
        + value + ".");
  }

}
