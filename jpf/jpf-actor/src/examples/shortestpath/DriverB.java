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
package shortestpath;

import osl.manager.Actor;
import osl.manager.ActorName;
import osl.manager.RemoteCodeException;
import osl.manager.annotations.message;

/**
 * This class is a Basset test driver for a actor-based implementation of a
 * shortest path algorithm.
 * 
 * This driver includes eight different graphs ranging in size from 4 to 5
 * nodes.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class DriverB extends Actor {

  int N = 1; // can specify 1 to 8
  ActorName a0, a1, a2, a3, a4;

  @message
  public void setUp(String[] args) throws RemoteCodeException {
    if (args.length >= 1) {
      N = Integer.parseInt(args[0]);
      if (N < 1 || N > 8) {
        throw new RuntimeException("Invalid graph number. Must be from 1 to 8");
      }
    }
    System.out.println("shortestpath graph number = " + N);

    // create root set actors
    a0 = create(SPActor.class, 1);
    a1 = create(SPActor.class, 2);
    a2 = create(SPActor.class, 3);
    a3 = create(SPActor.class, 4);
    a4 = create(SPActor.class, 5);

    // initialize actors
    switch (N) {
    case 1: // *******************************************
      call(a0, "addNeighbor", a1, 10);
      call(a0, "addNeighbor", a2, 10);
      call(a0, "addNeighbor", a3, 10);

      call(a1, "addNeighbor", a2, 10);
      call(a2, "addNeighbor", a1, 10);

      call(a2, "addNeighbor", a3, 10);
      call(a3, "addNeighbor", a2, 10);
      break;

    case 2: // *******************************************
      call(a0, "addNeighbor", a1, 10);
      call(a0, "addNeighbor", a2, 10);
      call(a0, "addNeighbor", a3, 10);

      call(a1, "addNeighbor", a2, 10);

      call(a2, "addNeighbor", a3, 10);
      break;

    case 3: // *******************************************
      call(a0, "addNeighbor", a1, 10);
      call(a1, "addNeighbor", a0, 10);

      call(a0, "addNeighbor", a2, 10);
      call(a2, "addNeighbor", a0, 10);

      call(a1, "addNeighbor", a3, 10);
      call(a3, "addNeighbor", a1, 10);

      call(a1, "addNeighbor", a4, 10);
      call(a4, "addNeighbor", a1, 10);
      break;

    case 4: // *******************************************
      call(a0, "addNeighbor", a1, 10);
      call(a0, "addNeighbor", a2, 10);

      call(a1, "addNeighbor", a2, 10);
      call(a1, "addNeighbor", a3, 10);

      call(a2, "addNeighbor", a3, 10);
      call(a2, "addNeighbor", a0, 10);

      call(a3, "addNeighbor", a0, 10);
      call(a3, "addNeighbor", a1, 10);

      break;

    case 5: // *******************************************
      call(a0, "addNeighbor", a1, 10);
      call(a1, "addNeighbor", a0, 10);

      call(a1, "addNeighbor", a2, 10);
      call(a2, "addNeighbor", a1, 10);

      call(a2, "addNeighbor", a3, 10);
      call(a3, "addNeighbor", a2, 10);

      break;

    case 6: // *******************************************
      call(a0, "addNeighbor", a1, 10);
      call(a1, "addNeighbor", a0, 10);

      call(a1, "addNeighbor", a2, 10);
      call(a2, "addNeighbor", a1, 10);

      call(a2, "addNeighbor", a3, 10);
      call(a3, "addNeighbor", a2, 10);

      call(a3, "addNeighbor", a0, 10);
      call(a0, "addNeighbor", a3, 10);

      break;

    case 7: // *******************************************
      call(a0, "addNeighbor", a1, 10);

      call(a1, "addNeighbor", a2, 10);
      call(a2, "addNeighbor", a1, 10);

      call(a1, "addNeighbor", a3, 10);
      call(a3, "addNeighbor", a1, 10);

      break;

    case 8: // *******************************************
      call(a0, "addNeighbor", a1, 10);
      call(a0, "addNeighbor", a2, 10);
      call(a0, "addNeighbor", a3, 10);

      call(a1, "addNeighbor", a2, 10);
      call(a2, "addNeighbor", a1, 10);

      call(a2, "addNeighbor", a3, 10);
      call(a3, "addNeighbor", a2, 10);

      call(a3, "addNeighbor", a1, 10);
      call(a1, "addNeighbor", a3, 10);
      break;

    default:
      throw new RuntimeException("Invalid graph number. Must be from 1 to 8");
    }
  }

  @message
  public void test(String[] args) {
    // queue initial message
    send(a0, "receive", 0, 0);
  }

}
