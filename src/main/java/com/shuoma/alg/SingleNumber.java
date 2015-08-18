package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

/**
 Given an array of integers, every element appears twice except for one. Find that single one.
 */
@Tag(algs = BitManipulation, dss = Array, references = LeetCode)
public class SingleNumber {
  public int singleNumber(int[] A) {
    if (A.length == 0)
      return -1;
    int ret = A[0];
    for (int i = 1; i < A.length; i++) {
      ret ^= A[i];
    }
    return ret;
  }
}
