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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import gov.nasa.jpf.actor.icore.IMessage;

import static gov.nasa.jpf.actor.util.Constants.*;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class MessageOrderer {

  /***********************************************************/
  // Given a list of messages this method returns a list of
  // messages ordered according to the current dpor ordering
  // heuristic. Currently this method is used to order only
  // enabled messages - not the potentially larger set of
  // pending messages.
  public static List<IMessage> orderByHeuristic(List<IMessage> messages) {
    List<IMessage> orderedMessages;
    int heuristic = Util.getIntegerProperty("basset.dpor_heuristic");

    switch (heuristic) {

    case HEURISTIC_LOW_RECEIVER_ID: {
      orderedMessages = heuristicLowReceiverId(messages);
      break;
    }
    case HEURISTIC_HIGH_RECEIVER_ID: {
      orderedMessages = heuristicHighReceiverId(messages);
      break;
    }
    case HEURISTIC_NONE:
    case HEURISTIC_QUEUE: {
      orderedMessages = heuristicQueue(messages);
      break;
    }
    case HEURISTIC_STACK: {
      orderedMessages = heuristicStack(messages);
      break;
    }
    case HEURISTIC_RANDOM: {
      orderedMessages = heuristicRandom(messages);
      break;
    }
    case HEURISTIC_LOW_RECEIVER_ENABLED_MESSAGE_COUNT: {
      orderedMessages = heuristicLowEnabledMessageCount(messages);
      break;
    }
    case HEURISTIC_HIGH_RECEIVER_ENABLED_MESSAGE_COUNT: {
      orderedMessages = heuristicHighEnabledMessageCount(messages);
      break;
    }
    case HEURISTIC_HIGH_ACTOR_SEND_AVERAGE: {
      orderedMessages = heuristicHighActorSendAverage(messages);
      break;
    }
    case HEURISTIC_SEND_GRAPH_REACHABILITY: {
      orderedMessages = heuristicSendGraphReachability(messages);
      break;
    }
    default:
      throw new RuntimeException("invalid +basset.dpor_heuristic: " + heuristic);
    }

    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicStack(List<IMessage> messages) {
    Vector<Integer> order = new Vector<Integer>();

    for (int i = messages.size() - 1; i >= 0; i--) {
      int receiver = messages.get(i).getReceiver().getID();
      if (!order.contains(Integer.valueOf(receiver))) {
        order.add(receiver);
      }
    }

    List<IMessage> orderedMessages = new ArrayList<IMessage>();
    while (!order.isEmpty()) {
      int receiver = order.remove(0);
      for (IMessage msg : messages) {
        if (msg.getReceiver().getID() == receiver) {
          orderedMessages.add(msg);
        }
      }
    }
    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicLowEnabledMessageCount(List<IMessage> messages) {
    Map<Integer, Integer> order = new HashMap<Integer, Integer>();

    for (int i = 0; i < messages.size(); i++) {
      int receiver = messages.get(i).getReceiver().getID();
      if (!order.containsKey(receiver)) {
        order.put(receiver, 1);
      } else {
        order.put(receiver, order.get(receiver) + 1);
      }
    }

    List<Integer> mapKeys = new Vector<Integer>(order.keySet());
    List<Integer> mapValues = new Vector<Integer>(order.values());

    List<Integer> sortedValues = new Vector<Integer>(mapValues);
    Collections.sort(sortedValues);

    int size = sortedValues.size();
    Integer[] orderedActors = new Integer[size];

    for (int i = 0; i < size; i++) {
      int index = mapValues.indexOf(sortedValues.get(i));
      orderedActors[i] = mapKeys.get(index);
      mapValues.set(index, 0);
    }

    List<IMessage> orderedMessages = new ArrayList<IMessage>();
    for (int i = 0; i < orderedActors.length; i++) {
      int receiver = orderedActors[i];
      for (IMessage msg : messages) {
        if (msg.getReceiver().getID() == receiver) {
          orderedMessages.add(msg);
        }
      }
    }
    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicHighEnabledMessageCount(List<IMessage> messages) {
    Map<Integer, Integer> order = new HashMap<Integer, Integer>();

    for (int i = 0; i < messages.size(); i++) {
      int receiver = messages.get(i).getReceiver().getID();
      if (!order.containsKey(receiver)) {
        order.put(receiver, 1);
      } else {
        order.put(receiver, order.get(receiver) + 1);
      }
    }

    List<Integer> mapKeys = new Vector<Integer>(order.keySet());
    List<Integer> mapValues = new Vector<Integer>(order.values());

    List<Integer> sortedValues = new Vector<Integer>(mapValues);
    Collections.sort(sortedValues, new Comparator<Integer>() {
      @Override
      public int compare(Integer arg0, Integer arg1) {
        return arg1.compareTo(arg0);
      }
    });

    int size = sortedValues.size();
    Integer[] orderedActors = new Integer[size];

    for (int i = 0; i < size; i++) {
      int index = mapValues.indexOf(sortedValues.get(i));
      orderedActors[i] = mapKeys.get(index);
      mapValues.set(index, 0);
    }

    List<IMessage> orderedMessages = new ArrayList<IMessage>();
    for (int i = 0; i < orderedActors.length; i++) {
      int receiver = orderedActors[i];
      for (IMessage msg : messages) {
        if (msg.getReceiver().getID() == receiver) {
          orderedMessages.add(msg);
        }
      }
    }
    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicRandom(List<IMessage> messages) {
    Vector<Integer> order = new Vector<Integer>();

    for (int i = 0; i < messages.size(); i++) {
      int receiver = messages.get(i).getReceiver().getID();
      if (!order.contains(Integer.valueOf(receiver))) {
        order.add(receiver);
      }
    }

    Integer[] orderArray = order.toArray(new Integer[] {});
    randomizeIntegerArray(orderArray);

    List<IMessage> orderedMessages = new ArrayList<IMessage>();
    for (int i = 0; i < orderArray.length; i++) {
      int receiver = orderArray[i];
      for (IMessage msg : messages) {
        if (msg.getReceiver().getID() == receiver) {
          orderedMessages.add(msg);
        }
      }
    }
    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicHighReceiverId(List<IMessage> messages) {
    List<IMessage> orderedMessages = new ArrayList<IMessage>(messages);
    Comparator<IMessage> compHigher = new Comparator<IMessage>() {
      @Override
      public int compare(IMessage message1, IMessage message2) {
        return message2.getReceiver().getID() - message1.getReceiver().getID();
      }
    };
    Collections.sort(orderedMessages, compHigher);
    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicLowReceiverId(List<IMessage> messages) {
    List<IMessage> orderedMessages = new ArrayList<IMessage>(messages);

    Comparator<IMessage> compLower = new Comparator<IMessage>() {
      @Override
      public int compare(IMessage message1, IMessage message2) {
        return message1.getReceiver().getID() - message2.getReceiver().getID();
      }
    };
    Collections.sort(orderedMessages, compLower);
    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicQueue(List<IMessage> messages) {
    Vector<Integer> order = new Vector<Integer>();
    for (int i = 0; i < messages.size(); i++) {
      int receiver = messages.get(i).getReceiver().getID();
      if (!order.contains(Integer.valueOf(receiver))) {
        order.add(receiver);
      }
    }

    List<IMessage> orderedMessages = new ArrayList<IMessage>();
    while (!order.isEmpty()) {
      int receiver = order.remove(0);
      for (IMessage msg : messages) {
        if (msg.getReceiver().getID() == receiver) {
          orderedMessages.add(msg);
        }
      }
    }
    return orderedMessages;
  }

  /***********************************************************/
  private static List<IMessage> heuristicHighActorSendAverage(List<IMessage> messages) {
    Map<Integer, Double> uniqueReceivers = new HashMap<Integer, Double>();

    for (int i = 0; i < messages.size(); i++) {
      int receiver = messages.get(i).getReceiver().getID();
      if (!uniqueReceivers.containsKey(receiver)) {
        uniqueReceivers.put(receiver, Util.getSendAverage(receiver));
      }
    }

    List<Integer> mapKeys = new Vector<Integer>(uniqueReceivers.keySet());
    List<Double> mapValues = new Vector<Double>(uniqueReceivers.values());

    // sort the values: highest value (actor send average) first
    List<Double> sortedValues = new Vector<Double>(mapValues);
    Collections.sort(sortedValues);
    Collections.reverse(sortedValues);

    // create an array of keys (receiver ids) sorted according
    // to their corresponding values (actor send averages)
    int size = sortedValues.size();
    Integer[] orderedActors = new Integer[size];
    for (int i = 0; i < size; i++) {
      int index = mapValues.indexOf(sortedValues.get(i));
      orderedActors[i] = mapKeys.get(index);
      mapValues.set(index, -1.0);
    }

    // create
    List<IMessage> orderedMessages = new ArrayList<IMessage>();
    for (int i = 0; i < orderedActors.length; i++) {
      int receiver = orderedActors[i];
      for (IMessage msg : messages) {
        if (msg.getReceiver().getID() == receiver) {
          orderedMessages.add(msg);
        }
      }
    }

    return orderedMessages;
  }

  /***********************************************************/
  // If (A can send to B) and (B cannot send to A), explore
  // messages to A first. Otherwise, do not reorder A and B
  private static List<IMessage> heuristicSendGraphReachability(List<IMessage> messages) {

    List<IMessage> orderedMessages = new ArrayList<IMessage>(messages);
    Comparator<IMessage> compHigher = new Comparator<IMessage>() {
      @Override
      public int compare(IMessage message1, IMessage message2) {
        int id1 = message1.getReceiver().getID();
        int id2 = message2.getReceiver().getID();
        boolean from1to2 = Util.hasPathto(id1, id2);
        boolean from2to1 = Util.hasPathto(id2, id1);

        // return a negative integer, zero, or a positive
        // integer as the first argument is less than,
        // equal to, or greater than the second.
        if (from1to2 == from2to1) {
          return id1 - id2; // the tie breaker is lowest
        } else if (from1to2) {
          return -1;
        } else {
          return 1;
        }
      }
    };
    Collections.sort(orderedMessages, compHigher);
    return orderedMessages;
  }

  /***********************************************************/
  private static void randomizeIntegerArray(Integer[] array) {
    int seed = Util.getIntegerProperty("basset.randomseed");
    Random generator = new Random(seed);

    for (int i = 0; i < array.length * 2; i++) {
      int index1 = generator.nextInt(array.length);
      int index2 = generator.nextInt(array.length);
      Integer temp = array[index1];
      array[index1] = array[index2];
      array[index2] = temp;
    }
  }

  /***********************************************************/
  public static List<IMessage> orderByPriority(List<IMessage> messages) {

    List<IMessage> orderedMessages = new ArrayList<IMessage>();
    /*
     * List<Integer> order = Util.getOrder(); for(Integer receiver : order){ for
     * (IMessage msg : messages) { if (msg.getReceiver().getID() == receiver) {
     * orderedMessages.add(msg); } } }
     */
    return orderedMessages;

  }

}
