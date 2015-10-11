///////////////////////////////////////////////////////////////////////
//// This is a modified version of the ActorFoundry file of the same
//// name.
////
//// The modification was done to support the Basset extension for
//// Java PathFinder (JPF).  For more details, see
////   http://mir.cs.illinois.edu/basset
////
//// Modification author: Steven Lauterburg <steven.lauterburg@gmail.com>
///////////////////////////////////////////////////////////////////////

/**
 * Copyright (c) 1998-2009  The University of Illinois Board of Trustees.
 * All Rights Reserved.
 * 
 * Distributed under license: http://osl.cs.uiuc.edu/af/LICENSE
 * 
 * Developed by: The Open Systems Lab
 *               University of Illinois at Urbana-Champaign
 *               Department of Computer Science
 *               Urbana, IL 61801
 *               http://osl.cs.uiuc.edu
 *
 * Contact: http://osl.cs.uiuc.edu/af
 *
 */
package osl.manager;

import osl.util.DeepCopy;

/**
 * This class packages a request to send a message. Instances of this class are
 * passed from actors to their managers in order to request a message send.
 * <p>
 * 
 * @author Mark Astley
 * @version $Revision: 1.7 $ ($Date: 1999/01/19 18:43:32 $)
 * @see ActorManager
 * @see ActorRequest
 */

public class ActorMsgRequest extends ActorRequest {
  /**
   * The sending actor's name.
   */
  public ActorName sender;

  /**
   * The receiving actor's name.
   */
  public ActorName receiver;

  /**
   * The method to be invoked on the receiver.
   */
  public String method;

  /**
   * The arguments to pass to the invoked method in the receiver. If no
   * arguments are required then THIS SHOULD BE AN ARRAY OF LENGTH 0, rather
   * than null.
   */
  public Object[] methodArgs;

  /**
   * Set to <b>true</b> if this message has blocked the sender waiting for a
   * reply, <b>false</b> otherwise.
   */
  public boolean RPCRequest;

  public boolean byCopy = false;

  /**
   * The default constructor. Note that this <b>DOES NOT</b> construct a legal
   * message.
   */
  public ActorMsgRequest() {
    this(null, null, null, null, false);
  }

  /**
   * The usual constructor that is used to build messages. This construct
   * accepts values for each of the message fields, but sets the
   * <em>RPCRequest</em> field to <b>false</b>.
   */
  public ActorMsgRequest(ActorName theSender, ActorName theReceiver,
                         String newStr, Object[] newArray) {
    this(theSender, theReceiver, newStr, newArray, false);
  }

  public ActorMsgRequest(ActorName theSender, ActorName theReceiver,
                         String newStr, Object[] newArray, boolean rpc,
                         boolean byCopy) {
    sender = theSender;
    receiver = theReceiver;
    method = newStr;
    methodArgs = newArray;
    RPCRequest = rpc;
    tags = null;
    this.byCopy = byCopy;
  }

  /**
   * The most general constructor. Allows every field except for the
   * <em>tags hashtable to be set to an arbitrary value.
   */
  public ActorMsgRequest(ActorName theSender, ActorName theReceiver,
                         String newStr, Object[] newArray, boolean rpc) {
    this(theSender, theReceiver, newStr, newArray, rpc, false);
  }

  /**
   * A useful method for debugging.
   */
  public String toString() {
    return "<ActorMsgRequest: " + super.toString() + " sender=" + sender
        + " receiver=" + receiver + " method=" + method + " numArgs="
        + methodArgs.length + " rpc?=" + RPCRequest + ">";
  }

  /**
   * Provide a "safe" clone of a message. A safe message clone is a regular
   * clone of the original message with the exception that a deep copy is
   * performed on the <em>methodArgs</em> field. This allows cloning to be used
   * for handing a message off during local message passing. That is, the
   * invariant is that the <em>methodArgs</em> structure must be fresh to avoid
   * hidden channels between user actors.
   * 
   * @exception osl.manager.ActorRequestCloneException
   *              Thrown as a wrapper for any error which occurs during clone
   *              creation. Typically, this exception is thrown due to a
   *              serialization error in the <tt>methodArgs</tt> field. This
   *              field is serialized in order to create a deep copy. We use a
   *              deep copy to prevent the creation of hidden channels between
   *              actors sharing a reference to a common object.
   */
  public Object clone() throws ActorRequestCloneException {
    // ActorFoundry's DeepCopy implementation uses java serialization
    // which is not support well in JPF. Instead Basset uses an MJI-based
    // DeepCopy implementation and make a copy of the entire
    // ActorMsgRequest object.
    ActorMsgRequest newCopy;
    try {
      newCopy = (ActorMsgRequest) DeepCopy.deepCopy(this);
    } catch (Exception e) {
      throw new ActorRequestCloneException("error cloning ActorMsgRequest", e);
    }
    return newCopy;

    // // First create a regular cloned copy.
    // ActorMsgRequest newCopy = (ActorMsgRequest) super.clone();
    //
    // // Now deep copy the methodArgs field.
    // try {
    // // This dirty trick knocks several ms off the standard cloning time
    // if (osl.foundry.FoundryStart.useNative) {
    // newCopy.methodArgs = new Object[methodArgs.length];
    // for (int i = 0; i < methodArgs.length; i++)
    // if ((methodArgs[i] != null)
    // && (DeepCopy.isMutable(methodArgs[i].getClass()
    // .toString())))
    // newCopy.methodArgs[i] = DeepCopy
    // .deepCopy((Serializable) methodArgs[i]);
    // else
    // newCopy.methodArgs[i] = methodArgs[i];
    // } else
    //
    // newCopy.methodArgs = new Object[methodArgs.length];
    // for (int i = 0; i < methodArgs.length; i++)
    // if ((methodArgs[i] != null)
    // && (DeepCopy.isMutableClass(methodArgs[i].getClass())))
    // newCopy.methodArgs[i] = DeepCopy
    // .deepCopy((Serializable) methodArgs[i]);
    // else
    // newCopy.methodArgs[i] = methodArgs[i];
    //
    // } catch (IOException e) {
    // // Serialization error for methodArgs
    // throw new ActorRequestCloneException(
    // "error serializing methodArgs in ActorMsgRequest", e);
    // } catch (Exception e) {
    // // Anything else is probably fatal but return it anyway since it
    // // is probably an error in one of the arguments in the
    // // methodArgs array.
    // // Log.logExceptionTrace(e);
    // throw new ActorRequestCloneException(
    // "error cloning ActorMsgRequest", e);
    // }
    //
    // // And return the clone.
    // return newCopy;

  }

}
