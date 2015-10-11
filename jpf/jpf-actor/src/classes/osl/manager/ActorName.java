///////////////////////////////////////////////////////////////////////
//// This file replaces the ActorFoundry file of the same name.
//// The code in the original file is not necessary for testing with
//// Basset as use of a name service is not supported or considered.
////
//// The file was created to support the Basset extension for
//// Java PathFinder (JPF).  For more details, see
////   http://mir.cs.illinois.edu/basset
////
//// Author: Steven Lauterburg <steven.lauterburg@gmail.com>
///////////////////////////////////////////////////////////////////////

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
package osl.manager;

import gov.nasa.jpf.actor.core.CoreActorName;

import java.io.Serializable;

/**
 * Instances of this class are used to represent actor names. Under normal
 * circumstances, actor programs do not have direct access to actor objects
 * themselves. The actor name provides abstraction of implementation and
 * location.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
@SuppressWarnings("serial")
public class ActorName extends CoreActorName implements Serializable {

  public ActorName() {
    super();
  }

}
