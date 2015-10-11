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
package scalatests.deadlock

import scala.actors.Actor
import scala.actors.Actor._

/**
 * 
 * @author Micro Dotta (mirco.dotta@gmail.com)
 * 
 */
object Ex1_Classic extends Application {

  val foo = actor {
    bar !? 'bar match {
      case 'foo => println("got foo")
      case 'bar => 
        println("got bar")
        reply('ok)
    }

    react {
      case 'bar =>
        assert(true)
        reply('ok)
      case any => assert(false)
    }
  }

  val bar: Actor  = actor {
    react {
      case 'bar =>
        pet !? 'go match {
          case 'ok => reply('foo)
        }
    } 
  }

  val pet = actor {
    react {
      case 'go =>
        foo !? 'bar
        reply('ok)
    }
  }

}

