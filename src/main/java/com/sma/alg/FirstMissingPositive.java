package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.InplaceSwap;
import static com.sma.util.ArrayUtil.swap;

import com.sma.annotation.Tag;

@Tag(dl = D2, dss = Array, references = LeetCode, tricks = InplaceSwap)
/**
 Given an unsorted integer array, find the first missing positive integer.
 Your algorithm should run in O(n) time and uses constant space.

 For example, given
 [1,2,0] return 3,
 [3,4,-1,1] return 2.
 */
public class FirstMissingPositive {
  public static void main(String[] args) {
    new FirstMissingPositive().firstMissingPositive(new int[] {2, 1});
  }

  public int firstMissingPositive(int[] A) {
    //idea: using the array it self as a hash table
    int n = A.length;
    if (n == 0) { return 1; }
    for (int i = 0; i < n; ) {
      int rightPlace = A[i] - 1;  //the right place to put num[i]
      //if meet the all following condition, do the swap
      //1. the current place is not the right place
      //2. the right place is in range of array
      //3. the number in right place does not equal to the current number
      if (rightPlace != i && rightPlace >= 0 && rightPlace < n && A[rightPlace] != A[i]) {
        swap(A, i, rightPlace);
      } else i++;
    }

    for (int i = 0; i < n; i++) {
      if (A[i] != i + 1) {
        return i + 1;
      }
    }
    return n + 1;
  }
}
