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
package gov.nasa.jpf.actor;

/**
 * A simple class used to collect statistics during the exploration of an actor
 * program.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class Statistics {

  public static int statActorCreateCount = 0;
  public static int statActorDestroyedCount = 0;
  public static int statActorTerminatedCount = 0;
  public static int statJPFTraceCount = 0;
  public static int statMessageSendCount = 0;
  public static int statMessageDeliveryCount = 0;
  public static int statMessageDeliveryFailCount = 0;
  public static int statDeadlocks = 0;
  public static int statAbortedTraceCount = 0;

  /**
   * Reset the statistic counts. This is useful when performing multiple
   * explorations as part of a single execution, e.g., running multiple test
   * cases
   */
  public static void resetExplorationStatistics() {
    statActorCreateCount = 0;
    statActorDestroyedCount = 0;
    statActorTerminatedCount = 0;
    statJPFTraceCount = 0;
    statMessageSendCount = 0;
    statMessageDeliveryCount = 0;
    statMessageDeliveryFailCount = 0;
    statDeadlocks = 0;
    statAbortedTraceCount = 0;
  }

}
