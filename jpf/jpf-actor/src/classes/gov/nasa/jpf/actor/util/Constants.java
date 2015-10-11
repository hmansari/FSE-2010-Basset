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
 * This class defines constants used to translate command line options for
 * Basset's dynamic partial-order reductions.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class Constants {

  // Constants for option basset.dpor
  public static final int DPOR_NONE = 0;
  public static final int DPOR_DCUTE = 1;
  public static final int DPOR_DCUTE_FAST = 2; // Earliest
  public static final int DPOR_PERSISTENT = 3;
  public static final int DPOR_PERSISTENT_FAST = 4;
  public static final int DPOR_TRANSISTENT = 5; // PERSISTENT + Locking
  public static final int DPOR_TRANSISTENT_FAST = 6;
  public static final int DPOR_PERSISTENT_SLEEP = 10;
  public static final int DPOR_TRANSISTENT_SLEEP = 11;

  // Constants for option basset.dpor_heuristic
  public static final int HEURISTIC_NONE = 0;
  public static final int HEURISTIC_LOW_RECEIVER_ID = 1;
  public static final int HEURISTIC_HIGH_RECEIVER_ID = 2;
  public static final int HEURISTIC_QUEUE = 3;
  public static final int HEURISTIC_STACK = 4;
  public static final int HEURISTIC_RANDOM = 5;
  public static final int HEURISTIC_LOW_RECEIVER_ENABLED_MESSAGE_COUNT = 6;
  public static final int HEURISTIC_HIGH_RECEIVER_ENABLED_MESSAGE_COUNT = 7;
  public static final int HEURISTIC_HIGH_ACTOR_SEND_AVERAGE = 8;
  public static final int HEURISTIC_SEND_GRAPH_REACHABILITY = 9;

}
