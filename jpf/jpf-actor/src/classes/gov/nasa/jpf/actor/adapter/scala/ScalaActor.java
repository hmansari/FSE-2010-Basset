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
package gov.nasa.jpf.actor.adapter.scala;

import java.util.HashMap;
import java.util.Map;

import scala.Function1;
import scala.PartialFunction;
import scala.actors.Actor;

import gov.nasa.jpf.actor.core.ActorState;
import gov.nasa.jpf.actor.core.CoreActor;
import gov.nasa.jpf.actor.core.CoreActorName;
import gov.nasa.jpf.actor.icore.IMessage;
import gov.nasa.jpf.annotation.FilterField;

/**
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class ScalaActor extends CoreActor {
  @FilterField
  private static Map<Actor, ScalaActor> map = new HashMap<Actor, ScalaActor>();

  private final Actor scalaRef;

  @SuppressWarnings("unchecked")
  protected Function1 waitingFor;

  private PartialFunction continuation;

  private boolean shouldExit = false;

  public boolean needToBeStarted = false;

  ScalaActor(Actor ref) {
    super(CoreActorName.createActorName(ref.toString()));
    assert ref != null : "Cannot create null ref scala actor";
    this.scalaRef = ref;
  }

  @Override
  public void bringToLife() {
    assert isSuspended();
    actorThread.startThread();
    needToBeStarted = true;
    notifyActorSuspended();
  }

  @Override
  public void deliver(IMessage msg) {
    super.deliver(msg);
    waitingFor = null;
  }

  @Override
  public boolean canMakeProgress() {
    // if it is terminated then we know by definition that
    // we cannot make progress
    if (isSuspended() || isTerminated() || isDestroyed())
      return false;

    // why not: return super.canMakeProgress() || shouldExit();
    return shouldExit();

    // System.out.println((isWaiting() || isWaitingOnReply()) && waitingFor !=
    // null);
    // return (isWaiting() || isWaitingOnReply()) && waitingFor != null;
    // this is a tricky and full of side-effects call (look at comments below)
    // boolean cond = isStartedAndSuspended();
    //
    // if (!cond && isTerminated())
    // return false;
    //
    // return shouldExit();
  }

  // private boolean isStartedAndSuspended() {
  // // if needToBeStart means that the user calls the `start` method on the
  // actor
  // // but internally we didn't yet ran the thread associated to the actor
  // if (needToBeStarted) {
  // // since the user called the `start` method, the actor is in SUSPENDED
  // status
  // // and the flag needToBeStarted has been turned to true in the precise
  // moment
  // // that the `start` method has been called
  // assert isSuspended() : this + "is in state " + getState();
  // needToBeStarted = false;
  // //What if we start fails: Steve
  // athread.yieldNow(); // here we actually yield to the thread associated to
  // the actor
  // //XXX: Then here we should be in a state were the actor is WAITING on its
  // mailbox.
  // // However it could also be the case that the actor is TERMINATED, but in
  // that
  // // case the `shouldExit` variable should be false. However, if the actor
  // got shutted
  // // down because it was linked to an actor that actually called the `exit()`
  // method,
  // // then we could end up in the situation where the actor is TERMINATED but
  // the `shouldExit`
  // // flag is still turned on. Hence in the `canMakeProcess()` method above we
  // handle this
  // // weird situation.
  // // XXX: All this nasty side effects should be removed and the following
  // assertion should hold.
  // //assert isWaiting() || (isTerminated() && !shouldExit()) : this +
  // "is in state "+getState()+ " and shouldexit="+shouldExit() ;
  // }
  // return !needToBeStarted && isWaiting();
  // }

  @SuppressWarnings("unchecked")
  @Override
  public boolean canBeDelivered(IMessage msg) {
    return (isWaiting() || isWaitingOnReply())
        && ((Boolean) waitingFor.apply(msg.getValue()));
  }

  @SuppressWarnings("unchecked")
  public void waitingFor(Object o) {
    assert o instanceof Function1;
    waitingFor = (Function1) o;
    // XXX: TODO The if part should be now completely useless. Need to be
    // checked...
    if (isSuspended()) {
      // XXX: This is a quick fix, since the currentThread in Scala is
      // encapsulated
      // in an actor (after even complete), it's state can be CREATED and be
      // asked
      // to wait for "any" message. If we call setState(WAITING) blindly, we
      // would
      // get an error because only ACTIVE actor can be put in a WAITING state.
      // As I said, quick fix but it should work.
      return;
    } else {
      if (!isWaitingOnReply())
        setState(ActorState.WAITING);
    }
  }

  public void setShouldExit(boolean value) {
    shouldExit = value;
  }

  public boolean shouldExit() {
    return shouldExit;
  }

  public void setContinuation(PartialFunction continuation) {
    this.continuation = continuation;
  }

  PartialFunction getContinuation() {
    PartialFunction res = continuation;
    continuation = null;
    return res;
  }

  boolean hasContinuation() {
    return continuation != null;
  }

  public void act() {
    // XXX: This is a bit of a hack ...
    scala.actors.Actor$.MODULE$.tl().set(scalaRef);
    scalaRef.act();
  }

  @Override
  public boolean isReady() {
    return isSuspended() && needToBeStarted;
  }

  public static ScalaActor getActor(Actor ref) {
    ScalaActor actor = map.get(ref);
    if (actor == null) {
      actor = (ScalaActor) CoreActor.createActor(ref);
      map.put(ref, actor);
    }

    return actor;
  }

}
