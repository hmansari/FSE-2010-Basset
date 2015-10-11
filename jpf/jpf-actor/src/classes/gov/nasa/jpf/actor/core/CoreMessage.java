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

import gov.nasa.jpf.actor.icore.IActorName;
import gov.nasa.jpf.actor.icore.IMessage;
import gov.nasa.jpf.annotation.FilterField;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
// This is not an abstract class because it is used internally for deadlock
// detection.
public class CoreMessage implements IMessage {

  @FilterField
  private static int idGen = 100;

  @FilterField
  private int id;
  @FilterField
  private VectorClock vc = new VectorClock();

  private IActorName receiver;
  private IActorName sender;
  private Object content;

  /*******************************************************/
  public CoreMessage(IActorName sender, Object content, IActorName receiver) {
    this.sender = sender;
    this.content = content;
    this.receiver = receiver;
    this.id = idGen++;
  }

  /*******************************************************/
  public IActorName getReceiver() {
    return receiver;
  }

  /*******************************************************/
  public IActorName getSender() {
    return sender;
  }

  /*******************************************************/
  public VectorClock getVectorClock() {
    return vc;
  }

  /*******************************************************/
  public void setVectorClock(VectorClock clock) {
    vc.setVector(clock.getVector());
  }

  /*******************************************************/
  public Object getValue() {
    return content;
  }

  /*******************************************************/
  public int getID() {
    return id;
  }

  /*******************************************************/
  @Override
  public String toString() {
    return "msg<sender: " + getSender() + ", cont: " + getValue()
        + ", receiver: " + getReceiver() + ">";
  }
}
