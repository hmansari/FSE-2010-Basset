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
package scalaexamples.util

trait Main {

  private var _input: Option[Int] = None
  private var _expected: Option[Int] = None

  def main(args: Array[String]) = {
    args.length match {
      case 0 => default()
      case 1 => 
        input = Integer.parseInt(args(0))
      case 2 =>
        input = Integer.parseInt(args(0))
        expected = Integer.parseInt(args(1))
      case n => error("don't know what to do with " + n + " parameters")
    }
    mainBody()
  }

  protected def input_=(in: Int): Unit = {_input = Some(in)}
  def input = _input

  protected def expected_=(expect: Int): Unit = {_expected = Some(expect)}
  def expected = _expected

  def default(): Unit
  def mainBody(): Unit

}
