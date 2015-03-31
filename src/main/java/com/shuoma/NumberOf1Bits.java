package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.BitOperation;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BitOperation, source = LeetCode)
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
