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
package gov.nasa.jpf.test.actor.foundrytests.test1;

import java.util.ArrayList;
import osl.manager.Actor;
import osl.manager.ActorName;
import osl.manager.RemoteCodeException;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class AAAA extends Actor {

  private int value = 4;
  private ActorName b;
  private ActorName c;

  public AAAA() {
    System.out.println("creating AAAA");
  }

  /*******************************************************/
  public void run() {
    System.out.println("AAAA is in RUN");
    try {
      this.b = create(BBBB.class);
      this.c = create(CCCC.class);
    } catch (RemoteCodeException e) {
      System.out.println("> AAAA: " + e);
    }

    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(value);
    list.add(value + 1);
    list.add(value + 2);
    list.add(value + 3);

    send(b, "setValue", list);
    send(c, "printValue", b);
    System.out.println("AAAA has ended");
  }

}
