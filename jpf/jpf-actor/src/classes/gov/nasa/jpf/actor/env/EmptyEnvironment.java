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

/**
 * With an EMPTY "environment" the framework will report a potential deadlock if
 * at the end of the execution there is some alive actor (it doesn't matter
 * whether they are blocked because of a synchronous call or they are just
 * waiting to process a message which they will never get --- since the cloud is
 * empty).
 * 
 * To activate the EMPTY "environment" your driver class (the class that
 * contains the program main and that is responsible of starting the program
 * that you want to model check) has to extend the TestDriver class (which can
 * be found in the same directory of the current file). In the "main" of your
 * driver insert the following method call as the first statement
 * 
 * setEnvironment(new EmptyEnvironment())
 * 
 * And the your program will be verified under the EMPTY "environment".
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class EmptyEnvironment extends Environment {
  public EmptyEnvironment() {
    super(false);
  }
}
