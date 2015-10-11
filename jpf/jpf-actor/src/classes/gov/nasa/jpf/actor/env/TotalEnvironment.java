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
 * With a TOTAL "environment" the framework will report a potential deadlock if
 * at the end of the execution there is some actor that is waiting_on_reply.
 * Therefore idle/waiting actors are *NOT* considered in a deadlock
 * 
 * The TOTAL "environment" is the default "environment" under which any test is
 * checked against within the framework, so you do not need to do anything
 * special for activating it.
 * 
 * If you want to make the environment declaration explicit you can however have
 * your driver class exten the TestDriver class (which can be found in the same
 * directory of the current file).
 * 
 * In the "main" of your driver insert the following method call as the first
 * statement
 * 
 *     setEnvironment(new TotalEnvironment())
 * 
 * And the your program will be verified under the TOTAL "environment" (again,
 * this procedure is not needed since it is performed by the framework
 * automagically).
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public final class TotalEnvironment extends Environment {
  public TotalEnvironment() {
    super(true);
  }
}
