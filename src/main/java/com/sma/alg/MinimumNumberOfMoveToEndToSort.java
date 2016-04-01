package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.sma.annotation.Tag.Reference.Interview;
import static com.sma.annotation.Tag.Trick.FromTwoEndsToMiddle;

import com.sma.annotation.Tag;

import java.util.Arrays;

/**
 You can only use one operation on a given array: move(int i): which move i-th element to the end
 of array. Given an array, find out how many move operation needed to make the array sorted.
 */
@Tag(dss = {Array, MonotonicSequence}, references = Interview, tricks = FromTwoEndsToMiddle)
public class MinimumNumberOfMoveToEndToSort {

  /**
   * the operation can move one element to the end of array, so every time select the non-sorted element
   to the end by increasing order, and the original increasing sequence doesn't need to move. The problem is
   transform to find the longest increasing subsequence, operation count = num.length - LIS count.
   The longest increasing subsequence can be found by compare original array with the sorted array
   */

  public static void main(String[] args){
    MinimumNumberOfMoveToEndToSort counter = new MinimumNumberOfMoveToEndToSort();
    int[] num = new int[]{4,1,3,5,2};
    System.out.println(counter.minMove(num)); //3
  }

  int minMove(int[] num) {
    int[] aux = new int[num.length];
    for (int i = 0; i < num.length; i++) aux[i] = num[i];
    Arrays.sort(aux);
    int index = 0;
    for (int i = 0; i < num.length; i++) {
      if (num[i] == aux[index]) index++;
    }
    return aux.length - index;
  }
}
