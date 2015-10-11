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

import gov.nasa.jpf.actor.core.CoreMessage;
import gov.nasa.jpf.actor.icore.IActorName;

/**
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class ScalaMsg extends CoreMessage {

  private Object replyTo;

  public ScalaMsg(IActorName sender, Object content, IActorName receiver,
                  Object replyTo) {
    super(sender, content, receiver);
    this.replyTo = replyTo;
  }

  public Object getReplyChannel() {
    return replyTo;
  }

}
