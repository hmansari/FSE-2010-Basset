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

/**
 * A vector clock implementation for use by Basset's dynamic partial order
 * reduction implementations
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class VectorClock {

  ArrayList<Integer> vector = new ArrayList<Integer>();

  public VectorClock() {
    vector = new ArrayList<Integer>();
  }

  public VectorClock(int size) {
    vector = new ArrayList<Integer>();
    for (int i = 0; i < size; i++)
      vector.add(0);
  }

  public VectorClock(ArrayList<Integer> v) {
    this.vector = (ArrayList<Integer>) v.clone();
  }

  public int getClock(int processIndex) {
    try {
      return vector.get(processIndex);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return -1;

  }

  public void setVector(ArrayList<Integer> v) {
    this.vector = (ArrayList<Integer>) v.clone();
  }

  public int setClock(int processIndex, int CValue) {
    if (processIndex < vector.size()) {
      vector.set(processIndex, CValue);
      return processIndex;
    } else if (processIndex == vector.size()) {
      vector.add(CValue);
      return processIndex;
    } else
      return -1;
  }

  public ArrayList<Integer> getVector() {
    return this.vector;
  }

  public boolean isGT(VectorClock newVC) {
    ArrayList<Integer> v = newVC.vector;
    if (v.size() == vector.size()) {
      for (int i = 0; i < vector.size(); i++) {
        if (vector.get(i) < v.get(i))
          return false;
      }
    }
    return true;
  }

  public boolean isLT(VectorClock newVC) {
    ArrayList<Integer> v = newVC.vector;
    if (v.size() == vector.size()) {
      for (int i = 0; i < vector.size(); i++) {
        if (vector.get(i) > v.get(i))
          return false;
      }
    }
    return true;
  }

  public VectorClock getMax(VectorClock newVC) {
    ArrayList<Integer> v = newVC.vector;

    VectorClock max = new VectorClock();
    if (v.size() == 0) {
      max.setVector(this.vector);
      return max;
    }

    if (v.size() == vector.size()) {
      for (int i = 0; i < vector.size(); i++) {
        if (vector.get(i) >= v.get(i))
          max.vector.add(vector.get(i));
        else
          max.vector.add(v.get(i));
      }
      return max;
    } else
      return null;
  }

  public String toString() {
    return vector.toString();
  }

}
