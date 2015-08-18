package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

/**
 Given an array of numbers nums, in which exactly two elements appear only once and
 all the other elements appear exactly twice.
 Find the two elements that appear only once.

 For example:
 Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 */
@Tag(algs = BitManipulation, dss = Array, references = LeetCode)
public class SingleNumberIII {

  public static void main(String[] args) {
    SingleNumberIII ins = new SingleNumberIII();
    System.out.println(Arrays.toString(ins.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
  }

  int[] singleNumber(int[] nums) {
    int xor = 0;
    for (int num : nums) {
      xor ^= num;
    }

    // extract the lowest set bit of xor
    int lowestSetBit = xor & ~(xor - 1);
    int[] ret = new int[2];
    for (int num : nums) {
      ret[(num & lowestSetBit) == 0 ? 0 : 1] ^= num;
    }
    return ret;
  }
}
