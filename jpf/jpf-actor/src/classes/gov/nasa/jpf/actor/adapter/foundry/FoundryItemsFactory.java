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
package gov.nasa.jpf.actor.adapter.foundry;

import gov.nasa.jpf.actor.adapter.IItemsFactory;
import gov.nasa.jpf.actor.icore.IMessage;
import gov.nasa.jpf.actor.util.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import osl.manager.Actor;
import osl.manager.ActorCreateRequest;
import osl.manager.ActorName;
import osl.manager.basic.BasicActorImpl;
import osl.util.ConstructorStructure;
import osl.util.MethodStructureVector;

/**
 * 
 * @author Steven Lauterburg (steven.lauterburg@gmail.com)
 * 
 */
public class FoundryItemsFactory implements IItemsFactory {

  /***********************************************************/
  public Object[] createActorObjects(Object ref) {
    ActorCreateRequest request = (ActorCreateRequest) ref;
    Class<?> actorClass = request.behToCreate;
    Object[] conArgs = request.constructorArgs;

    ActorName newActorName = null;
    Actor newActor = null;
    BasicActorImpl newActorThread = null;

    try {
      // TODO: These checks are problematic because we currently allow
      // actors to be created in the test drivers using PlatformUtil.
      // Two sanity checks first
      // if (!(Actor.getClassRef().isAssignableFrom(request.behToCreate)))
      // throw new SecurityException(
      // "Error: only subclasses of Actor may be created!");
      // if (!(ActorImpl.getClassRef()
      // .isAssignableFrom(request.implToCreate)))
      // throw new SecurityException(
      // "Error: only subclasses of ActorImpl may be used to implement new actors!");

      // create new actor behavior
      newActor = createActorClass(actorClass, conArgs);

      // extract new actor name from the actor object
      newActorName = (ActorName) newActor.getActorName();

      // create new actor implementation (i.e., thread)
      newActorThread = new BasicActorImpl((Actor) newActor);

      ActorCreateRequest copyReq = (ActorCreateRequest) request; // .clone();
      newActorThread.actorInitialize(null, newActorName, copyReq);

      // update new actor with some information from the
      // implementation object
      newActor._init(newActorThread);

    } catch (Exception e) {
      // TODO: We may want to throw a RemoteCodeException
      // here. This needs to be looked into some more.
      // RemoteCodeException("Error performing create", e);
      throw new RuntimeException(e.getMessage());
    }

    return new Object[] { newActorName, newActor, newActorThread };
  }

  /***********************************************************/
  protected Actor createActorClass(Class<?> actorClass, Object[] conArgs) {

    // create the actor behavior object.
    Actor newActor = null;
    try {
      if (conArgs == null || conArgs.length == 0) {
        newActor = (Actor) actorClass.newInstance();
      } else {
        Constructor<?> constructor = findConstructor(actorClass, conArgs);
        newActor = (Actor) constructor.newInstance(conArgs);
      }

    } catch (InstantiationException e) {
      Logger.error(this, "% ActorThread.setBehavior: " + e);
      e.printStackTrace();

      throw new RuntimeException(e.getMessage());
    } catch (InvocationTargetException e) {
      Logger.error(this, "% ActorThread.setBehavior: " + e);
      e.printStackTrace();

      throw new RuntimeException(e.getMessage());
    } catch (IllegalAccessException e) {
      Logger.error(this, "% ActorThread.setBehavior: " + e);
      e.printStackTrace();

      throw new RuntimeException(e.getMessage());
    } catch (NoSuchMethodException e) {
      Logger.error(this, "% ActorThread.setBehavior: " + e);
      e.printStackTrace();

      throw new RuntimeException(e.getMessage());
    }

    return newActor;
  }

  /***********************************************************/
  private Constructor<?> findConstructor(Class<?> classType, Object[] args)
      throws NoSuchMethodException {

    // grab all public constructors.
    Constructor<?>[] caConstructor = classType.getConstructors();
    if (caConstructor.length == 0) {
      throw new NoSuchMethodException("Class '" + classType
          + "' has no public constructors!");
    }

    // find a proper constructor and return it.
    for (int i = 0; i < caConstructor.length; i++) {
      Class<?>[] caParameterType = caConstructor[i].getParameterTypes();

      if (args.length != caParameterType.length) {
        continue;
      }

      boolean bFound = true;
      for (int j = 0; j < args.length; j++) {
        if (caParameterType[j].isPrimitive()) {
          if (args[i] == null) {
            bFound = false;
            break;
          } else if (caParameterType[i] == Integer.TYPE
              && !(args[i] instanceof Integer)) {
            bFound = false;
            break;
          } else if (caParameterType[i] == Long.TYPE
              && !(args[i] instanceof Long)) {
            bFound = false;
            break;
          } // add more primitives as needed
        } else if ((args[i] != null)
            && (!caParameterType[i].isInstance(args[i]))) {
          bFound = false;
          break;
        }
      }

      if (bFound) {
        return caConstructor[i];
      }
    }

    // if cannot find a proper constructor then report an error.
    throw new NoSuchMethodException("No constructor for '" + classType
        + Arrays.toString(args) + "'.");
  }

  /***********************************************************/
  // This version of findConstructor was originally taken from
  // ActorFoundry's BasicActorImpl class. However, it does not
  // seem to handle primitive arguments properly. For now we
  // will use the variant above.
  protected Constructor<?> findConstructorAlt(Class<?> classType, Object[] args)
      throws NoSuchMethodException {

    Object[] zeroArray = new Object[0];
    int i;
    boolean found = false;
    Constructor<?> toInvoke = null;
    Constructor<?>[] cons = null;

    // Grab set of constructors, signal error if no public constructors
    cons = classType.getConstructors();
    if (cons.length == 0)
      throw new NoSuchMethodException("Class " + classType
          + " has no public constructors!");

    // First build a method structure vector for all the
    // constructors. This automatically sorts the constructors so
    // that the first match is the most specific constructor.
    MethodStructureVector items = new MethodStructureVector();
    for (i = 0; i < cons.length; i++)
      items.insertElement(new ConstructorStructure(cons[i], cons[i]
          .getParameterTypes()));

    // Now scan the constructor list for the most specific constructor
    ConstructorStructure next = null;
    Object[] sorted = new Object[items.size()];
    items.copyInto(sorted);

    if (args == null) {
      args = zeroArray;
    }
    for (i = 0; ((i < sorted.length) && (!found)); i++) {
      next = (ConstructorStructure) sorted[i];

      // Compare argument list lengths
      if (args.length != next.argTypes.length)
        continue;

      // Found a possible candidate, compare argument types
      found = true;
      toInvoke = next.meth;
      for (int j = 0; j < args.length; j++)
        if (!next.argTypes[j].isInstance(args[j])) {
          found = false;
          break;
        }
    }

    if (!found) {
      String argList = "(";

      if ((args == null) || (args.length == 0))
        argList = argList + ")";
      else {
        for (i = 0; i < args.length - 1; i++)
          argList = argList + args[i].getClass() + ", ";
        argList = argList + args[i].getClass() + ")";
      }

      throw new NoSuchMethodException("No constructor for " + classType
          + " with arg types " + argList);
    }

    // Found the constructor, so return it
    return toInvoke;
  }

  /***********************************************************/
  public IMessage createMessage(Object... args) {
    // TODO Auto-generated method stub
    return null;
  }

}
