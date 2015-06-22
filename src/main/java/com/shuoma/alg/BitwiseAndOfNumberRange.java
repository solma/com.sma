package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BitManipulation, references = LeetCode)
public class BitwiseAndOfNumberRange {

  //http://math.stackexchange.com/questions/1073532/how-to-find-bitwise-and-of-all-numbers-for-a-given-range
  public int rangeBitwiseAnd(int m, int n) {
    int xor = m ^ n;
    int cnt = 0;
    while (xor > 0) {
      xor >>= 1;
      cnt++;
    }
    return m & n & ~((1 << cnt) - 1);
  }
}
