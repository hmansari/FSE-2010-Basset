///////////////////////////////////////////////////////////////////////
//// This file replaces the ActorFoundry file of the same name.
//// ActorFoundry's DeepCopy implementation used Java serialization
//// which is not supported well in JPF. Instead Basset uses an 
//// MJI-based implementation that copies the entire object.
////
//// The file was created to support the Basset extension for
//// Java PathFinder (JPF).  For more details, see
////   http://mir.cs.illinois.edu/basset
////
//// Author: Steven Lauterburg <steven.lauterburg@gmail.com>
///////////////////////////////////////////////////////////////////////
package osl.util;

/**
 * This class provides a utility to create a deep copy of an arbitrary object.
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class DeepCopy {

  /**
   * A deep copy of an arbitrary object is created using an MJI-based copier
   * that recursively parses each of the object's fields to create a replica.
   */
  public static Object deepCopy(Object original) {
    // [TODO] Should this deepCopy itself be a native method?
    return gov.nasa.jpf.actor.core.Util.copy(original);
  }

}
