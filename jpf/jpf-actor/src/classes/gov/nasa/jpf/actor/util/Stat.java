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
package gov.nasa.jpf.actor.util;

/**
 * Utility class which provides access to actor exploration statistics
 * at the application level.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class Stat {

  public static native void incStatActorCreateCount();

  public static native void incStatActorDestroyedCount();

  public static native void incStatActorTerminatedCount();

  public static native void incStatJPFTraceCount();

  public static native void incStatMessageDeliveryCount();

  public static native void incStatMessageDeliveryFailCount();

  public static native void incStatMessageSendCount();

  public static native void incStatDeadlocks();

  public static native void incStatAbortedTraceCount();

  public static native int getStatJPFTraceCount();

}
