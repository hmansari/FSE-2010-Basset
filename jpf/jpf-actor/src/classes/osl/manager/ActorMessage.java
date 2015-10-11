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
package osl.manager;

import gov.nasa.jpf.actor.core.CoreMessage;

import java.io.Serializable;

/**
 * Instances of this class are messages used to send information from one actor
 * to another.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class ActorMessage extends CoreMessage implements Serializable {

  private String method; // method name
  private Object[] args; // list of arguments
  private boolean isReturnRequest; // if a return message is required
  private String errorMessage; // error message

  private boolean isReturnMessage = false; // if this is a return message
  private boolean isErrorMessage = false; // if this is an error message

  private static final String EMPTY_STRING = "";

  // TODO: Possibly turn this into a wrapper for ActorFoundry ActorMsgRequests
  public ActorMessage(ActorName sender, ActorName receiver, String method,
                      Object[] args, boolean returnRequest) {

    super(sender, new Object[] { method, args }, receiver);

    this.method = method;
    this.args = (args == null) ? new Object[0] : args;
    this.isReturnRequest = returnRequest;
    this.errorMessage = EMPTY_STRING;
  }

  public final String getMethod() {
    return method;
  }

  public final Object[] getArguments() {
    return args;
  }

  public boolean isReturnRequested() {
    return isReturnRequest;
  }

  private void setReturnMessage(Object returnObject) {
    isReturnMessage = true;

    args = new Object[1];
    args[0] = returnObject;

    // TODO: Should the return object be sent by copy instead?
    // Should we match how the original message sent?
    // m_objaArgs[0] = ObjectHandler.deepCopy((Serializable)p_objReturn);
  }

  public boolean isReturnMessage() {
    return isReturnMessage;
  }

  private void setErrorMessage(String p_strMsg) {
    isErrorMessage = true;
    isReturnMessage = true;

    errorMessage = p_strMsg;
  }

  public final String getErrorMessage() {
    return errorMessage;
  }

  public boolean isErrorMessage() {
    return isErrorMessage;
  }

  public ActorMessage makeReturnMessage(Object replyObject) {
    ActorMessage replyMessage = new ActorMessage((ActorName) getReceiver(),
        (ActorName) getSender(), method, args, false);
    replyMessage.setReturnMessage(replyObject);
    return replyMessage;
  }

  public ActorMessage makeErrorMessage(String strErrorMsg) {
    ActorMessage errorMessage = new ActorMessage((ActorName) getReceiver(),
        (ActorName) getSender(), method, args, false);
    errorMessage.setErrorMessage(strErrorMsg);
    return errorMessage;
  }

  public String toString() {
    String strReturn = new String();
    String strLineSeparator = System.getProperty("line.separator");

    strReturn += "Actor Message: " + strLineSeparator;
    strReturn += "    - Sender:   " + getSender() + strLineSeparator;
    strReturn += "    - Receiver: " + getReceiver() + strLineSeparator;

    strReturn += "    - Return Request (RPC) Flag: " + isReturnRequest
        + strLineSeparator;
    strReturn += "    - Return Message Flag: " + isReturnMessage
        + strLineSeparator;
    strReturn += "    - Error Message Flag:  " + isErrorMessage
        + strLineSeparator;

    if (isErrorMessage) {
      strReturn += "    - Error Message: " + errorMessage + strLineSeparator;
    }

    if (isReturnMessage) {
      strReturn += "    - Return Value: " + (args.length > 0 ? args[0] : "")
          + strLineSeparator;
    } else {
      strReturn += "    - Method Name: " + method + strLineSeparator;
      strReturn += "    - Arguments : #. " + args.length + strLineSeparator;

      for (int i = 0; i < args.length; i++) {
        strReturn += "        - [" + i + "]: " + args[i] + strLineSeparator;
      }
    }

    return strReturn;
  }

}
