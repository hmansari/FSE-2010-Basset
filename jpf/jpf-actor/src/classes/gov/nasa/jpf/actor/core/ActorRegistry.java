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
import gov.nasa.jpf.actor.icore.IActorName;
import gov.nasa.jpf.actor.icore.IActorThread;
import gov.nasa.jpf.actor.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class maintains lists of active and terminated actor threads. It also
 * provides utilities to register new actor threads, deregister existing actor
 * threads, and to allow the Basset platform to query the information in the
 * lists in various ways.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class ActorRegistry {

  private final List<IActorThread> active;

  private final List<IActorThread> terminated;

  public ActorRegistry() {
    this.active = new ArrayList<IActorThread>();
    this.terminated = new ArrayList<IActorThread>();
  }

  void register(IActorThread athread) {
    assert !active.contains(athread);
    active.add(athread);
    Logger.verbose(this, "registered " + athread);
  }

  void unregister(IActorThread athread) {
    assert active.contains(athread);
    boolean removed = active.remove(athread);
    if (removed) {
      Logger.verbose(this, "unregistered " + athread);
      terminated.add(athread);
    } else {
      Logger.error(this, "cannot unregister " + athread);
    }
  }

  IActorThread get(IActorName name) {
    IActorThread athread = findActorThread(active, name);
    if (athread == null) {
      Logger.debug(this, "Couldn't find " + name
          + " in the list of alive actors");
      athread = findActorThread(terminated, name);
      if (athread == null)
        Logger.debug(this, "Couldn't find " + name
            + " in the list of terminated actors");
    }

    return athread;
  }

  private IActorThread findActorThread(List<IActorThread> list, IActorName name) {
    IActorThread athread = null;
    for (int i = list.size() - 1; i >= 0; i--) {
      athread = list.get(i);
      if (athread != null && athread.getActorName().equals(name)) {
        return athread;
      }
    }
    return null;
  }

  List<IActorThread> getReadyActorThreads() {
    List<IActorThread> ready = new ArrayList<IActorThread>();
    for (IActorThread athread : active) {
      if (athread.getAttached().isReady())
        ready.add(athread);
    }
    return ready;
  }

  List<IActor> getWaitingOnReplyActors() {
    List<IActor> result = new ArrayList<IActor>();

    IActor actor = null;
    for (IActorThread athread : active) {
      actor = athread.getAttached();
      if (actor.isWaitingOnReply())
        result.add(actor);
    }

    return result;
  }

  List<IActor> getWaitingActors() {
    List<IActor> result = new ArrayList<IActor>();

    IActor actor = null;
    for (IActorThread athread : active) {
      actor = athread.getAttached();
      if (actor.isWaiting())
        result.add(actor);
    }

    return result;
  }

  @Override
  public String toString() {
    return "ActorRegistry";
  }

}
