package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BitManipulation;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = BitManipulation, references = LeetCode)
public class PowerOfTwo {
  public boolean isPowerOfTwo(int n) {
    if (n == 0 || n == Integer.MIN_VALUE) return false;
    return (n & (n - 1)) == 0;
  }
}
