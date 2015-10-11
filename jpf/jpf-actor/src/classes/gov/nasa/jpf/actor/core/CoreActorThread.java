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
import gov.nasa.jpf.actor.icore.IMessage;
import gov.nasa.jpf.annotation.FilterField;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public abstract class CoreActorThread extends Thread implements IActorThread {

  protected final CoreActor attached;
  @FilterField
  public VectorClock vc;

  protected CoreActorThread(CoreActor actor) {
    this.attached = actor;
    setName(attached.getActorName().getName());
    actor.attachToThreadActor(this);
    this.vc = new VectorClock(20);
    // System.out.println("== creating at " + this + " event clock: " +
    // this.vc);
  }

  @Override
  public void run() {
    while (isActorAlive()) {
      processDeliveredMessage();

      if (isActorAlive()) {
        attached.beWaiting();
        yieldToPlatform();
      }
    }
    unsuspendPlatformThread(); // TODO: Why is this done? Why not
                               // yieldToPlatform?
  }

  private boolean isActorAlive() {
    return attached.isActive() || attached.isWaiting()
        || attached.isWaitingOnReply();
  }

  abstract protected void processDeliveredMessage();

  final protected void unsuspendPlatformThread() {
    Util.unsuspendPlatformThread();
  }

  @Override
  final public void yieldToPlatform() {
    Platform.getInstance().yieldNow();
  }

  @Override
  public void yieldNow() {
    Util.yieldTo(this);
  }

  @Override
  public IActor getAttached() {
    return attached;
  }

  @Override
  public VectorClock getVectorClock() {
    return vc;
  }

  @Override
  public void setVectorClock(VectorClock clock) {
    vc.setVector(clock.getVector());
  }

  @Override
  public void deliver(IMessage msg) {
    attached.deliver(msg);
  }

  @Override
  public void startThread() {
    Util.startThreadInInterruptedStatus(this);
  }

  @Override
  // TODO: this will be a problem when we implement become...
  // the name may need to be associated with the thread
  // if the actor object is swapped when the behavior
  // changes.
  public IActorName getActorName() {
    return attached.getActorName();
  }

  @Override
  public String toString() {
    return "ActorThread<" + attached + ">";
  }

}
