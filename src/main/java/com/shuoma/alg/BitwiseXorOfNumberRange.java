package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;

import com.shuoma.annotation.Tag;

// http://stackoverflow.com/questions/10670379/find-xor-of-all-numbers-in-a-given-range/10670524#10670524
@Tag(algs = BitManipulation)
public class BitwiseXorOfNumberRange {

  public int getXor(int a, int b) {
    return f(b) ^ f(a - 1);
  }

  int f(int a) {
    int[] res = {a, 1, a + 1, 0};
    return res[a % 4];
  }
}
