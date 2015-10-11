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

import scala.actors.ExitActorException;
import scala.actors.KillActorException;
import scala.actors.SuspendActorException;

import gov.nasa.jpf.actor.core.CoreActorThread;
import gov.nasa.jpf.actor.util.Logger;

/**
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class ScalaActorThread extends CoreActorThread {

  ScalaActorThread(ScalaActor actor) {
    super(actor);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void run() {
    // XXX: notify actor that the thread has started
    // XXX: actor should be set to active now
    ScalaActor actor = (ScalaActor) getAttached();
    actor.needToBeStarted = false;
    assert actor.isSuspended() : this + " is in state " + actor.getActorState();
    actor.beActive();

    boolean init = true;
    while (actor.hasContinuation() || init) {
      try {
        if (init) {
          init = false;
          actor.act();
        } else
          actor.getContinuation().apply(null);
      }

      catch (Throwable e) {
        if (e instanceof SuspendActorException) {
          Logger.debug("Caught " + e + " in " + this);// do nothing
        } else if (e instanceof ExitActorException) {
          Logger.debug("Caught " + e + " in " + this);
          break;
        } else if (e instanceof KillActorException) {
          Logger.debug("Caught " + e + " in " + this);
        } else {
          throw new RuntimeException(e);
        }
      }
    }

    actor.beTerminated();
    unsuspendPlatformThread();
  }

  @Override
  protected void processDeliveredMessage() {
  }

}
