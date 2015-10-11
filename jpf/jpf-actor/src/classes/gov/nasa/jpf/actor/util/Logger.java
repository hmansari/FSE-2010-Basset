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
package gov.nasa.jpf.actor.util;

import gov.nasa.jpf.actor.core.Util;

import java.io.PrintStream;

/**
 * Logging utility to be used by code at the application level.
 * 
 * @author Mirco Dotta (mirco.dotta@gmail.com)
 * 
 */
public class Logger {

  private static enum LogLevel {
    DEBUG, VERBOSE, WARNING, SEVERE, INFO, ERROR
  }

  private static PrintStream out = System.out;
  private static PrintStream err = System.err;

  public static void debug(String msg) {
    if (isDebugMode())
      log(out, LogLevel.DEBUG, msg);
  }

  public static void debug(Object obj, String msg) {
    if (isDebugMode())
      log(out, LogLevel.DEBUG, obj, msg);
  }

  public static void warning(String msg) {
    log(out, LogLevel.WARNING, msg);
  }

  public static void warning(Object obj, String msg) {
    log(out, LogLevel.WARNING, obj, msg);
  }

  public static void severe(String msg) {
    log(out, LogLevel.SEVERE, msg);
  }

  public static void severe(Object obj, String msg) {
    log(out, LogLevel.SEVERE, obj, msg);
  }

  public static void info(String msg) {
    if (isInfoMode())
      log(out, LogLevel.INFO, msg);
  }

  public static void verbose(String msg) {
    if (isVerboseMode())
      log(out, LogLevel.VERBOSE, msg);
  }

  public static void verbose(Object obj, String msg) {
    if (isVerboseMode())
      log(out, LogLevel.VERBOSE, obj, msg);
  }

  public static void error(Object obj, String msg) {
    if (isErrorMode())
      log(err, LogLevel.ERROR, obj, msg);
  }

  public static void error(String msg) {
    if (isErrorMode())
      log(err, LogLevel.ERROR, msg);
  }

  public static void log(PrintStream stream, LogLevel level, String msg) {
    stream.println(level + ": " + msg);
  }

  public static void log(PrintStream stream, LogLevel level, Object obj,
                         String msg) {
    log(stream, level, obj + " - " + msg);
  }

  public static void log(PrintStream stream, LogLevel level, Object obj) {
    stream.println(level + "(object): " + obj);
  }

  public static boolean isVerboseMode() {
    return Util.getBooleanProperty("basset.debug")
        || Util.getBooleanProperty("basset.verbose");
  }

  public static boolean isDebugMode() {
    return Util.getBooleanProperty("basset.debug");
  }

  public static boolean isInfoMode() {
    return Util.getBooleanProperty("basset.info");
  }

  public static boolean isErrorMode() {
    return Util.getBooleanProperty("basset.debug")
        || Util.getBooleanProperty("basset.error");
  }

}
