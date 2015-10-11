///////////////////////////////////////////////////////////////////////
//// This is a modified version of the file
////   http://lampsvn.epfl.ch/svn-repos/scala/scala/trunk/src/actors/scala/actors/ActorProxy.scala@14660
//// [TODO] Or was it this other file?  Check with Mirco!
////   http://lampsvn.epfl.ch/svn-repos/scala/scala/tags/R_2_7_2_final/src/actors/scala/actors/ActorProxy.scala
////
//// The modification was done to support the Basset extension for
//// Java PathFinder (JPF).  For more details, see
////   http://mir.cs.illinois.edu/basset
////
//// Modification author: Mirco Dotta <mirco.dotta@gmail.com>
///////////////////////////////////////////////////////////////////////

/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2005-2007, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// $Id: ActorProxy.scala 14660 2008-04-15 15:56:51Z michelou $


package scala.actors


import java.lang.Thread

/**
 * The class <code>ActorProxy</code> provides a dynamic actor proxy for normal
 * Java threads.
 *
 * @version 0.9.8
 * @author Philipp Haller
 */
private[actors] class ActorProxy(t: Thread) extends Actor {

  def act() {}

  /**
   * Terminates with exit reason <code>'normal</code>.
   */
  override def exit(): Nothing = {
    gov.nasa.jpf.actor.adapter.scala.ScalaActor.getActor(this).setShouldExit(false)
    // links
    if (!links.isEmpty)
      exitLinked()
    throw new InterruptedException
  }

}
