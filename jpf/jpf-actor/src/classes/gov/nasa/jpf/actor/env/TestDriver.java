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
 * This class provides the infrastructure for setting/getting the "environment" 
 * of a test. The concept of "environment" is used to define when an actor system 
 * is in a deadlock. 
 * 
 * Possible "environments" are:
 * 
 *    - TOTAL  (class TotalEnvironment)
 *    - EMPTY  (class EmptyEnvironment)
 *    - CUSTOM (subclass of EmptyEnvironment that the developer needs to provide)
 *    
 * TOTAL ENVIRONMENT:
 * -----------------
 *  
 * This is the default environment, so you don't need to do or change anything.
 *
 *
 * EMPTY Environment
 * -----------------
 *
 * In the "main" of your driver insert the following method call
 * 
 *    setEnvironment(new EmptyEnvironment())
 * 
 * This is all you need to do.
 * 
 * CUSTOM Environment
 * ------------------
 *
 * This is the more interesting one. First you will have to create your own
 * environment that models the program that you want to verify. This will look
 * something like the following (in Scala, but it is close enough in Java):
 *
 * class MyEnv extends gov.nasa.jpf.actor.env.EmptyEnvironment {
 *   override def finalMessages() = java.util.Arrays.asList("foo", "bar")
 * }
 *
 * You will notice that the "MyEnv" class extends "EmptyEnvironment", which is the class that is used for specyfing the EMPTY environment discussed in the previousbullet. What we accomplished in "MyEnv" is that we have provided a finer specification of the state in which actors are assumed to be at the end of an execution, i.e., if the actors can deliver one (or more) of the messages returned by the "finalMessages" method, then the application is not in a deadlock.
 *
 * After having declared the above class you will simply add the following line:
 * 
 *     setEnvironment(new MyEnv())
 * 
 * as the first instruction in the "main" of your test driver class.
 * 
 * Some example to practically see how this feature is used can be found in
 *     
 *     'framework/extension/actor/examples/scala/examples/env/'
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 *
 */
public class TestDriver {
  private static Environment environment = new TotalEnvironment();

  protected void setEnvironment(Environment env) {
    this.environment = env;
  }

  public Environment getEnvironment() {
    return environment;
  }

}
