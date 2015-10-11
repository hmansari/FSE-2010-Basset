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

import gov.nasa.jpf.actor.icore.IActorName;
import gov.nasa.jpf.annotation.FilterField;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class CoreActorName implements IActorName {
  @FilterField
  public final static String JPF_ACTOR_NAME = "JPF_Actor-";
  @FilterField
  private final static String EMPTY_PREFIX = "";
  @FilterField
  private final static String NAME_SEP_LEFT = "[";
  @FilterField
  private final static String NAME_SEP_RIGHT = "]";
  @FilterField
  private final static List<CoreActorName> names = new ArrayList<CoreActorName>();
  @FilterField
  private static int idGen = 0;
  @FilterField
  private final String name;
  @FilterField
  private final int id;

  protected CoreActorName() {
    this(EMPTY_PREFIX);
  }

  public CoreActorName(String prefix) {
    this.id = idGen++;
    this.name = JPF_ACTOR_NAME + id + NAME_SEP_LEFT + prefix + NAME_SEP_RIGHT;
    assert !exists(this);
    names.add(this);
  }

  public static CoreActorName createActorName() {
    return new CoreActorName();
  }

  public static CoreActorName createActorName(String prefix) {
    return new CoreActorName(prefix);
  }

  public static boolean exists(CoreActorName that) {
    return names.contains(that);
  }

  public final String getName() {
    return name.toString();
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  // public boolean isComparable(Object that) {
  // return that instanceof ActorNameImpl;
  // }

  @Override
  public boolean equals(Object o) {
    if (o instanceof CoreActorName) {
      CoreActorName that = (CoreActorName) o;
      return getName().equals(that.getName());// && isComparable(o);
    }
    return false;
  }

  // @Override
  // public String toString() {
  // return name;
  // }

  @Override
  public int getID() {
    return id;
  }

}
