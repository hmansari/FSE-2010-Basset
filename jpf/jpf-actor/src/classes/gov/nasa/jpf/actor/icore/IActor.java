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
package gov.nasa.jpf.actor.icore;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public interface IActor extends IActorStateEvent, IPlatformListener {

  IActorName getActorName();

  void bringToLife();

  boolean canMakeProgress();

  boolean canBeDelivered(IMessage msg);

  void deliver(IMessage msg);

  IMessage getReceived();

  void send(IMessage msg);

  void call(IMessage msg);

  int getMailboxSize();

  boolean isSuspended();

  boolean isReady();

  boolean isActive();

  boolean isWaiting();

  boolean isWaitingOnReply();

  boolean isTerminated();

  void attachToThreadActor(IActorThread actorThread);

  IActorThread getAttachedActorThread();

  public boolean equalsActorName(IActorName that);

  public boolean equalsActorName(IActor that);

}
