package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BitManipulation, reference = LeetCode)
public class NumberOf1Bits {
  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {
    int cnt = 0;
    for (int i = 0; i < 32; i++) {
      cnt += getBit(n, i);
    }
    return cnt;
  }

  public int getBit(int n, int ith) {
    return n >> ith & 1;
  }
}
