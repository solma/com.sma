package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;
import static com.sma.util.ArrayUtil.swap;

import com.sma.annotation.Tag;

/**
 Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.
 */
@Tag(dss = Array, references = LeetCode, tricks = {TwoOrMorePointers})
public class MoveZeros {

  void moveZeroes(int[] nums) {
    int storeIdx = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        swap(nums, storeIdx++, i);
      }
    }
  }
}
