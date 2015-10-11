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

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import osl.manager.ActorName;

import gov.nasa.jpf.actor.IUserDriverStarter;
import gov.nasa.jpf.actor.adapter.IItemsFactory;
import gov.nasa.jpf.actor.adapter.foundry.FoundryItemsFactory; //import gov.nasa.jpf.actor.adapter.scala.ScalaItemsFactory;
import gov.nasa.jpf.actor.core.Platform;
import gov.nasa.jpf.actor.core.PlatformUtil;
import gov.nasa.jpf.actor.core.Util;

/**
 * This is the main class for the Basset tool.
 * 
 * Based on information it receives from JPF as arguments and properties that it
 * extracts from the configuration, it instantiates the controlling platform
 * object and the test driver for the actor program to be tested. The test
 * driver is executed so as to establish the initial conditions for the
 * exploration.
 * 
 * Once initial conditions have been established, Basset starts the
 * exploration.
 * 
 * TODO: Currently, this class is dependent on the current instantiations of the
 * framework (foundry and scala). This is not an ideal implementation, as code
 * would need to be added here for any new instantiations for additional actor
 * languages.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class Basset {

  private final static String SCALA = "scala";
  private final static String ACTOR_FOUNDRY = "foundry";

  /**
   * Explore the behavior of the provided test subject.
   * 
   */
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    // assert args.length >= 1 : "args[0] is the user subject test driver";
    if (args.length < 1) {
      showOptions();
      System.exit(0);
    }

    String subjectDriver = args[0];
    String[] subjectArgs = Arrays.copyOfRange(args, 1, args.length);

    System.out.println("subject: " + subjectDriver);
    System.out.println("subject args: " + Arrays.toString(subjectArgs));

    String language = Util.getProperty("basset.language");
    if (language == null)
      throw new RuntimeException("language not specified");
    System.out.println("subject language: " + language + "\n");

    Platform platform = null;

    if (language.equals(SCALA)) {

      try {
        Class factoryClass = (Class) Class
            .forName("gov.nasa.jpf.actor.adapter.scala.ScalaItemsFactory");
        IItemsFactory factory = (IItemsFactory) factoryClass.newInstance();
        platform = new Platform(factory);
        platform.setTestDriverName(subjectDriver);
        Class<IUserDriverStarter> executorClass = (Class<IUserDriverStarter>) Class
            .forName("scala.actors.jpf.ScalaProgramExecutor");
        IUserDriverStarter executor = executorClass.newInstance();

        // message interleavings are explored during execution of
        // driver's main method
        execMain(executor, args);
        platform.test();

      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (language.equals(ACTOR_FOUNDRY)) {
      platform = new Platform(new FoundryItemsFactory());
      platform.setTestDriverName(subjectDriver);
      try {
        // create test driver actor
        String driverClassName = args[0];
        Class<?> driverClass = Class.forName(driverClassName);
        ActorName driver = PlatformUtil.createActor(driverClass);

        // create a String array containing the arguments for the test driver
        String[] driverArgs = new String[args.length - 1];
        System.arraycopy(args, 1, driverArgs, 0, args.length - 1);

        // TODO: check if driver has a setup message. It is okay
        // if it does not... just skip to test.

        // message interleavings are NOT explored during setup
        PlatformUtil.send(driver, "setUp", (Serializable) driverArgs);
        platform.setUp();

        // TODO: check if driver has a test message. This is not
        // okay... throw an exception

        // message interleavings are explored during test
        PlatformUtil.send(driver, "test", (Serializable) driverArgs);
        platform.test();

      } catch (Exception e) {
        e.printStackTrace();
      }

    } else {
      throw new UnsupportedOperationException(
          "Don't know what to do with language '" + language + "'");
    }

  }

  private static void showOptions() {
    // #JPF Usage: java [<vm-option>..] gov.nasa.jpf.JPF [<jpf-option>..] [<app>
    // [<app-arg>..]]
    // # <jpf-option> : -help : print usage information
    // # | -log : print configuration initialization steps
    // # | -show : print configuration dictionary contents
    // # | +<key>=<value> : add or override key/value pair to config dictionary
    // # <app> : *.jpf application properties file pathname | fully qualified
    // application class name
    // # <app-arg> : arguments passed into main() method of application class
    System.out.println("");
    System.out
        .println("Usage: bin/jpf [<jpf-or-basset-option>..] gov.nasa.jpf.actor.Basset <subject> [<subject-arg>..] ");
    System.out.println("");
    System.out.println("available basset options:");
    System.out.println("  +basset.time_limit         (default=2147483647)");
    System.out.println("  +basset.tracestats         (default=false)");
    System.out.println("  +basset.explorationstats   (default=true)");
    System.out.println("  +basset.randomseed         (default=0)");
    System.out.println("");
    System.out.println("  +basset.dpor               (default=0)  0=None");
    System.out.println("                                          1=Dcute");
    System.out
        .println("                                          2=Dcute+Earliest");
    System.out
        .println("                                          3=Persistent");
    System.out
        .println("                                          4=Persistent+Earliest");
    System.out
        .println("                                          5=Transistent");
    System.out
        .println("                                          10=Persistent+Sleep");
    System.out
        .println("                                          11=Transistent+Sleep");
    System.out.println("");
    System.out
        .println("  +basset.dpor_heuristic     (default=0)  0=none (none is equivalent to queue (FIFO)");
    System.out
        .println("                                          1=low_receiver_id (ECA)  2=high_receiver_id (LCA)");
    System.out
        .println("                                          3=queue (FIFO)  4=stack (LIFO)  5=random (RAND)");
    System.out
        .println("                                          6=low_receiver_enabled_message_count (LEM)");
    System.out
        .println("                                          7=high_receiver_enabled_message_count (HEM)");
    System.out
        .println("                                          8=high_receiver_message_send_average (HMS)");
    System.out
        .println("                                          9=send_graph_reachability (SGR)");
    System.out.println("");

  }

  /**
   * Execute the main method for the specified test driver.
   * 
   * This method uses reflection to find the main method for a test driver and
   * then calls the language specific executor to invoke this static method.
   * 
   */
  public static void execMain(IUserDriverStarter executor, String[] args)
      throws IllegalAccessException, InvocationTargetException,
      SecurityException, NoSuchMethodException, ClassNotFoundException {

    // extract the class name for the subject test driver
    String driverClassName = args[0];

    // create a String array containing the arguments for the test driver
    String[] mainArgs = new String[args.length - 1];
    System.arraycopy(args, 1, mainArgs, 0, args.length - 1);

    // fetch the main method for the driver program that needs to be called
    Class<?> driverClass = Class.forName(driverClassName);
    Class<?>[] argTypes = new Class<?>[] { String[].class };
    java.lang.reflect.Method mainMethod = driverClass.getMethod("main",
        argTypes);

    // invoke the test driver program's main method passing one
    // argument (i.e. a String array of arguments)
    executor.exec(mainMethod, new Object[] { mainArgs });
  }

}
