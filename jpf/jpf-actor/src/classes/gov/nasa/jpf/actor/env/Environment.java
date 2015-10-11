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
package gov.nasa.jpf.actor.env;

import java.util.List;

/**
 * The concept of "environment" is used to define when an actor system is in a
 * deadlock. Possible "environments" are:
 * 
 * - TOTAL (class TotalEnvironment)
 * 
 * - EMPTY (class EmptyEnvironment)
 * 
 * - CUSTOM (subclass of EmptyEnvironment that the developer needs to provide)
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public abstract class Environment {
  private final boolean isTotal;

  Environment(boolean isTotal) {
    this.isTotal = isTotal;
  }

  public List<Object> finalMessages() {
    return null;
  }

  public boolean isTotal() {
    return isTotal;
  }
}