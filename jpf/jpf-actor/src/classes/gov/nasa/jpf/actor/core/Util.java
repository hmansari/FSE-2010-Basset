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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * @author Rajesh K. Karmani (rkumar8@illinois.edu)
 * 
 */
public class Util {

  public static native Object copy(Object toCopy);

  public static native boolean getTimeout();

  public static native boolean isUsingActorStateSerializer();

  public static native void terminateSearch();

  public static native boolean isSearchTerminated();

  /*******************************************************
   * Support for "actor send average" ordering heuristic
   *******************************************************/
  public static native void addSendCount(int receiver, int count);

  public static native double getSendAverage(int receiver);

  /*******************************************************
   * Support for ordering policy
   *******************************************************/
  public static native void setOrder(List<Integer> ord);

  public static native List<Integer> getOrder();

  public static native void changeToHighandLowOrder(int high, int low);

  /*******************************************************
   * Support for "graph reachability" ordering heuristic
   *******************************************************/
  public static native void addEdge(int sender, int receiver);

  public static native boolean hasPathto(int sender, int receiver);

  /*******************************************************
   * Support for thread switching
   *******************************************************/
  public static native void startThreadInInterruptedStatus(Thread thread);

  public static native void yieldToPlatform();

  public static native void yieldTo(Object thread);

  public static native void unsuspendPlatformThread();

  /*******************************************************
   * Support for properties
   *******************************************************/
  public static native boolean getBooleanProperty(String key);

  public static native int getIntegerProperty(String key);

  public static native String getProperty(String key);

  /*******************************************************
   * Support for DPOR
   *******************************************************/

  public static native int getIntAndForceChoiceGenerator(int min, int max);

  public static native int getCurrentCG();

  public static native void setNeedsDelayFlag(int index);

  public static native void addToPersistentSet(int currentCG, int messageId,
                                               int receiverId);

  public static native void addToPersistentSet_Sleep(int currentCG,
                                                     int messageId,
                                                     int receiverId);

  public static native void addToTransistentSet(int currentCG, int messageId,
                                                int receiverId);

  public static native void addToTransistentSet_Sleep(int currentCG,
                                                      int messageId,
                                                      int receiverId);

  public static native int getIntDPORDcute(int min, int max, int[] receivers);

  public static native int getIntDPORPersistent(int min, int max,
                                                int[] enabledMsgIdArray,
                                                int[] receivers,
                                                boolean expandOnlyOnce);

  public static native int getIntDPORPersistentSleep(int min, int max,
                                                     int[] enabledMsgIds,
                                                     int[] receivers,
                                                     boolean expandOnlyOnce);

  public static native void removeFromPersistent(int currentCG, int dealyed);

  public static <T> List<T> asList(T... e) {
    return new ArrayList<T>(Arrays.asList(e));
  }

  public static int[] convert2intArray(List<Integer> linearization) {
    int[] result = new int[linearization.size()];
    for (int i = 0; i < linearization.size(); i++)
      result[i] = linearization.get(i).intValue();
    return result;
  }

}
