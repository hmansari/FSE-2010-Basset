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

import gov.nasa.jpf.actor.adapter.IItemsFactory;
import gov.nasa.jpf.actor.icore.IActor;
import gov.nasa.jpf.actor.icore.IActorName;
import gov.nasa.jpf.actor.icore.IActorThread;

/**
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class ScalaItemsFactory implements IItemsFactory {

  public Object[] createActorObjects(Object scalaRef) {
    assert scalaRef instanceof scala.actors.Actor;

    // create new actor behavior
    IActor newActor = new ScalaActor((scala.actors.Actor) scalaRef);

    // extract new actor name from the actor object
    IActorName newActorName = newActor.getActorName();

    // create new actor implementation (i.e., thread)
    IActorThread newActorThread = new ScalaActorThread((ScalaActor) newActor);

    return new Object[] { newActorName, newActor, newActorThread };
  }

  public ScalaMsg createMessage(Object... params) {
    assert params.length == 4;
    Object sender = params[0];
    Object value = params[1];
    Object receiver = params[2];
    Object replyCh = params[3];
    if (receiver instanceof IActorName && sender instanceof IActorName)
      return new ScalaMsg((IActorName) sender, value, (IActorName) receiver,
          replyCh);
    else if (receiver instanceof IActor && sender instanceof IActor) {
      return new ScalaMsg(((IActor) sender).getActorName(), value,
          ((IActor) receiver).getActorName(), replyCh);
    } else
      throw new RuntimeException(this + " failed to create a Scala message");
  }

  @Override
  public String toString() {
    return "ScalaAdapterFactory";
  }

}
