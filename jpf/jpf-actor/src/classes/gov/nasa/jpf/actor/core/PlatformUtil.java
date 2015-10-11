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
package gov.nasa.jpf.actor.core;

import gov.nasa.jpf.actor.icore.IActor;

import java.io.Serializable;

import osl.manager.Actor;
import osl.manager.ActorCreateRequest;
import osl.manager.ActorMessage;
import osl.manager.ActorName;

/**
 * This class is a set of utilities for developing test drivers. It was
 * developed primarily for use with ActorFoundry subjects but could be used for
 * other instantiations as well.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class PlatformUtil {

  private static final Platform platform = Platform.getInstance();

  private static final ActorName platformActor = new ActorName();

  /**
   * This is a hack for test drivers. Ordinarily, actors should only be
   * accessible through their ActorName. This has been added to support
   * initialization of actors without sending them messages. This allows users
   * to set up a test without exploring the interleaving of initialization
   * messages.
   */
  /*******************************************************/
  public static Actor getActor(ActorName name) {
    return (Actor) platform.getActor(name);
  }

  /*******************************************************/
  public static ActorName createActor(String className, Serializable... args) {
    Class<?> classType = null;
    try {
      classType = Class.forName(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return createActor(classType, args);
  }

  /*******************************************************/
  public static ActorName createActor(Class<?> classType, Serializable... args) {
    IActor actor = null;
    ActorCreateRequest req = new ActorCreateRequest(null, classType, null,
        args, null);
    actor = platform.createActor(req);
    actor.bringToLife();
    return (ActorName) actor.getActorName();
  }

  /*******************************************************/
  public static void send(ActorName receiver, String method,
                          Serializable... args) {
    sendMessage(receiver, method, args);
  }

  /*******************************************************/
  private static void sendMessage(ActorName receiver, String method,
                                  Object[] args) {
    ActorMessage amMsg = new ActorMessage(platformActor, receiver, method,
        args, false);
    platform.push(amMsg, true);
  }

}
