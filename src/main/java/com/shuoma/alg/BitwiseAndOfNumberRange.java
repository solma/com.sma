package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BitManipulation, source = LeetCode)
public class BitwiseAndOfNumberRange {

  //http://math.stackexchange.com/questions/1073532/how-to-find-bitwise-and-of-all-numbers-for-a-given-range
  public int rangeBitwiseAnd(int m, int n) {
    int x = m ^ n;
    int s = x >> 1;
    while (s > 0) {
      x |= s;
      s >>= 1;
    }
    return m & n & ~x;
  }
}
