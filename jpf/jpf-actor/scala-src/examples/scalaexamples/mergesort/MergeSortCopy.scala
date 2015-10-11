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
package scalaexamples.mergesort

import scala.actors._
import scala.actors.Actor._

import scalaexamples.util.Main

object MergeSortCopy extends Main {

  def default() = {
    input = 3
  }

  def mainBody() = {
    val size = input.get
    var numbers: Array[Int] = new Array[Int](size) 
    val rand = new scala.util.Random(32);
    
    for(i <- 0 to size -1)
      numbers(i) = rand.nextInt(size*size)
    
    //println(myList.mkString("",", ",""))

    // Start the sort and wait till its done
    new MergePass() ! ((numbers, 'start))
  }
}

class MergePass extends Actor {
 
    start
    
    def act() = react {
      case (msg: Array[Int],'start) => checkIsSorted(sort(msg))
      case msg: Array[Int]          => reply(sort(msg))
    }
    

    /**
     * Wikipedia:
     * 
     * function merge_sort(m)
     *   var list left, right, result
     *   if length(m) <= 1
     *     return m
     *   // This calculation is for 1-based arrays. For 0-based, use length(m)/2 - 1.
     * 	 var middle = length(m) / 2
     *   //slice
     *   for each x in m up to middle
     *     add x to left
     *   for each x in m after middle
     *     add x to right
     *   
     *   left = merge_sort(left)
     *   right = merge_sort(right)
     * 	 
     *   if left.last_item > right.first_item
     *     result = merge(left, right)
     *   else
     *     result = append(left, right)
     * 
     *   return result
     * 
     */
    def sort(arr: Array[Int]): Array[Int] = {
        if(arr.length <= 1)
            return arr

        var num = arr.length/2
        var left = new MergePass()
        var right = new MergePass()
        
        // slice array in two halves
        val aleft = arr.slice(0,num).force
        val aright = arr.slice(num,arr.length).force 
        left ! aleft // XXX: if inlined hungs  
        right ! aright // XXX: if inlined hungs 
        
        receive { //XXX: with react this hangs, should investigate!!
          case tmp1: Array[Int] =>
            receive { //XXX: with react this hangs, should investigate!!
              case tmp2: Array[Int] =>
                if(tmp1.last > tmp2.first)
                  merge(tmp1,tmp2)
                else 
                  tmp1 ++ tmp2
            }
        }
    }
    
    
    /**
     * Wikipedia:
     * 
     * function merge(left,right)
     *   var list result
     * 	 while length(left) > 0 and length(right) > 0
     *     if first(left) ≤ first(right)
     *       append first(left) to result
     * 	     left = rest(left)
     *     else
     * 	     append first(right) to result
     *       right = rest(right)
     *   end while
     *   
     *   while length(left) > 0 
     *     append left to result
     *     while length(right) > 0 
     *       append right to result
     *   
     *   return result
     * 
     */
    private def merge(left: Array[Int], right: Array[Int]): Array[Int] = {
      val total = left.length+right.length
      val result = new Array[Int](total)
      var left_i, right_i, total_i = 0
      
      while (left_i < left.length && right_i < right.length) {
        if(left(left_i) < right(right_i)) {
          result(total_i) = left(left_i)
          left_i = left_i + 1
        } else {
          result(total_i) = right(right_i)
          right_i = right_i + 1
        }
        total_i = left_i + right_i
      }
      
      
      while(left_i < left.length) {
        result(total_i) = left(left_i)
        left_i = left_i + 1
        total_i = total_i + 1
      }
      
      
      while(right_i < right.length) {
        result(total_i) = right(right_i)
        right_i = right_i + 1
        total_i = total_i + 1
      }
      result
    }
    
    private def checkIsSorted(numbers: Array[Int]) = {
      val size = numbers.length
      for(i <- 0 to size-2 if numbers(i) > numbers(i+1)) 
      assert(false, "List not sorted. "+numbers.mkString("",", ",""))
    
      //println("sorted list: "+myList.mkString("",", ",""))
    }  
}

