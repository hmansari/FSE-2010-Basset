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

import java.util.Date;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;

/**
 * This class serves two primary purposes.
 * 
 * First, it prints out relevant statistics when an exploration completes.
 * 
 * And second, it facilitates a timeout capability for Basset. Following the
 * processing of each delivered message, the current running time for the
 * exploration is compared to a user specified time limit (in seconds). If the
 * running time has exceeded the limit the exploration is gracefully terminated.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class BassetListener extends ListenerAdapter {

  Config config;
  public static boolean timeout = false;
  static Date started;
  long timeLimit;

  public void searchStarted(Search search) {
    config = search.getConfig();
    started = new Date();
    timeLimit = config.getInt("basset.time_limit", Integer.MAX_VALUE);
    timeLimit = 14400; // 14400 seconds = 4 hours
  }

  public void stateAdvanced(Search search) {
    Date current = new Date();
    long elapsed = current.getTime() - started.getTime();
    if (elapsed > timeLimit * 1000) {
      timeout = true;
    }
  }

  public void searchFinished(Search search) {
    System.out
        .println("====================================================== basset statistics");

    if (timeout) {
      System.out.println("Basset time limit exceeded - " + timeLimit
          + " seconds.");
    }
    System.out.println("@@@@@@ end of exploration statistics:");
    System.out.println("@@@@@@   # of traces: " + Statistics.statJPFTraceCount);
    System.out.println("@@@@@@   # of traces with undelivered messages: "
        + Statistics.statMessageDeliveryFailCount);
    System.out.println("@@@@@@   # of traces aborted due to sleep sets: "
        + Statistics.statAbortedTraceCount);
    System.out.println("@@@@@@   # of messages delivered: "
        + Statistics.statMessageDeliveryCount);
    System.out.println("@@@@@@   # of actors created: "
        + Statistics.statActorCreateCount);
    System.out.println("@@@@@@   # of actors destroyed: "
        + Statistics.statActorDestroyedCount);
    System.out.println("@@@@@@   # of actors terminated: "
        + Statistics.statActorTerminatedCount);
    System.out.println("@@@@@@   # of deadlocks: " + Statistics.statDeadlocks);
  }

}
