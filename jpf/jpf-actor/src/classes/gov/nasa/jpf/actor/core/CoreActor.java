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

import java.util.ArrayList;
import java.util.List;

import gov.nasa.jpf.actor.icore.IActor;
import gov.nasa.jpf.actor.icore.IActorName;
import gov.nasa.jpf.actor.icore.IActorStateListener;
import gov.nasa.jpf.actor.icore.IActorThread;
import gov.nasa.jpf.actor.icore.IMessage;
import gov.nasa.jpf.actor.util.Logger;
import gov.nasa.jpf.actor.util.Stat;
import gov.nasa.jpf.annotation.FilterField;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public abstract class CoreActor implements IActor {

  private final IActorName actorName;
  protected CoreActorThread actorThread;
  private ActorState state = ActorState.EMPTY;
  public CoreMessage received;

  @FilterField
  private List<IActorStateListener> stateListeners = new ArrayList<IActorStateListener>();

  public CoreActor(IActorName name) {
    this.actorName = name;
    setState(ActorState.SUSPENDED);
  }

  @Override
  public void attachToThreadActor(IActorThread actorThread) {
    // TODO: It appears that this method is being called twice from
    // somewhere... The following assertion fails, but fortunately,
    // it is always setting the same value
    // assert this.actorThread == null;
    this.actorThread = (CoreActorThread) actorThread;
  }

  @Override
  public IActorThread getAttachedActorThread() {
    assert actorThread != null;
    return actorThread;
  }

  @Override
  public void bringToLife() {
    assert isSuspended();
    beWaiting();
    actorThread.startThread();
  }

  public void beSuspended() {
    setState(ActorState.SUSPENDED);
  }

  public void beActive() {
    setState(ActorState.ACTIVE);
  }

  public void beWaiting() {
    setState(ActorState.WAITING);
  }

  public void beWaitingOnReply() {
    setState(ActorState.WAITING_ON_REPLY);
  }

  public void beTerminated() {
    Stat.incStatActorTerminatedCount();
    setState(ActorState.TERMINATED);
  }

  public void beDestroyed() {
    Stat.incStatActorDestroyedCount();
    setState(ActorState.DESTROYED);
  }

  protected void setState(ActorState nextState) {
    assert nextState != ActorState.EMPTY;

    switch (nextState) {
    case SUSPENDED:
      assert !isDestroyed();
      state = nextState;
      notifyActorSuspended();
      break;

    case ACTIVE:
      assert isSuspended() || isWaiting() || isWaitingOnReply();
      state = nextState;
      notifyActorActive();
      break;

    case WAITING:
      assert (isSuspended() || isActive()) : this + " in state " + state;
      state = nextState;
      notifyActorWaiting();
      break;

    case WAITING_ON_REPLY:
      assert (isSuspended() || isActive()) : this + " in state " + state;
      state = nextState;
      notifyActorWaitingOnReply();
      break;

    case TERMINATED:
      // assert state == ActorState.ACTIVE || state == ActorState.WAITING;
      state = nextState;
      notifyActorTerminated();
      break;

    case DESTROYED:
      state = nextState;
      notifyActorDestroyed();
      break;

    default:
      assert false : "Dont know what to do with next state " + nextState;
      state = nextState;
    }
    Logger.debug(this, " is now in state [" + state + "]");
  }

  public void platformExplorationEnded() {
    if (canMakeProgress())
      actorThread.yieldNow();
  }

  public void addStateListener(IActorStateListener listener) {
    stateListeners.add(listener);
  }

  public void removeStateListener(IActorStateListener listener) {
    stateListeners.remove(listener);
  }

  // TODO: this is only used by Scala code and it is important to prioritize
  // actors that the user has `start`. This will all disappear once the
  // platform main loop is refactored with the new concept of "any action
  // (start/message/exit) is a scheduling relevant event
  protected void notifyActorSuspended() {
    for (IActorStateListener listener : stateListeners)
      listener.notifyActorSuspended(this);
  }

  protected void notifyActorActive() {
    for (IActorStateListener listener : stateListeners)
      listener.notifyActorActive(this);
  }

  protected void notifyActorWaiting() {
    for (IActorStateListener listener : stateListeners)
      listener.notifyActorWaiting(this);
  }

  protected void notifyActorWaitingOnReply() {
    for (IActorStateListener listener : stateListeners)
      listener.notifyActorWaitingOnReply(this);
  }

  protected void notifyActorTerminated() {
    for (IActorStateListener listener : stateListeners)
      listener.notifyActorTerminated(this);
  }

  private void notifyActorDestroyed() {
    for (IActorStateListener listener : stateListeners)
      listener.notifyActorDestroyed(this);
  }

  public abstract boolean canBeDelivered(IMessage msg);

  /**
   * This is always and solely called by the Platform thread.
   */
  @Override
  public boolean canMakeProgress() {
    return isWaiting();
  }

  @Override
  public void deliver(IMessage imsg) {
    CoreMessage msg = (CoreMessage) imsg;
    Logger.debug(this, "received message " + msg);
    received = msg;
    beActive();
  }

  @Override
  public IActorName getActorName() {
    return actorName;
  }

  public ActorState getActorState() {
    return state;
  }

  @Override
  public IMessage getReceived() {
    IMessage result = received;
    received = null;
    return result;
  }

  @Override
  public boolean isActive() {
    return state == ActorState.ACTIVE;
  }

  @Override
  public boolean isSuspended() {
    return state == ActorState.SUSPENDED;
  }

  @Override
  public boolean isWaiting() {
    return state == ActorState.WAITING;
  }

  @Override
  public boolean isWaitingOnReply() {
    return state == ActorState.WAITING_ON_REPLY;
  }

  @Override
  public boolean isReady() {
    // TODO: this method should be deleted and the Scala code depending on it
    // should be refactored somehow
    throw new UnsupportedOperationException("Ready state doesn't exist anymore");
  }

  @Override
  public boolean isTerminated() {
    return state == ActorState.TERMINATED;
  }

  public boolean isDestroyed() {
    return state == ActorState.DESTROYED;
  }

  @Override
  public String toString() {
    return getActorName().getName();
  }

  @Override
  public void call(IMessage msg) {
    // TODO: This should possibly also store for what (Object) answer
    // the actor is waiting
    Platform.getInstance().push(msg, false); // athread);
    beWaitingOnReply();
    Util.yieldToPlatform();
  }

  public void call(Object... args) {
    call(createMsg(args));
  }

  @Override
  public void send(IMessage msg) {
    Platform.getInstance().push(msg, false); // athread);
  }

  //TODO: dead code?
  public void send(Object... args) {
    send(createMsg(args));
  }

  @Override
  public int getMailboxSize() {
    return Platform.getInstance().getMailboxSize(getActorName());
  }

  public static CoreMessage createMsg(Object... args) {
    return (CoreMessage) Platform.getInstance().createMessage(args);
  }

  public static CoreActor createActor(Object ref) {
    return (CoreActor) Platform.getInstance().createActor(ref);
  }

  @Override
  public boolean equalsActorName(IActor that) {
    return getActorName().equals(that.getActorName());
  }

  @Override
  public boolean equalsActorName(IActorName that) {
    return getActorName().equals(that);
  }

}
