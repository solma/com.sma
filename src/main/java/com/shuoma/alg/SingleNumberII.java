package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BitManipulation, dss = Array, references = LeetCode)
public class SingleNumberII {
  public int singleNumber(int[] A) {
    int ones = 0, twos = 0;
    int nextOnes, nextTwos;
    for (int a : A) {
      nextOnes = (~a & ones) | (a & ~ones & ~twos);
      nextTwos = (~a & twos) | (a & ones);
      ones = nextOnes;
      twos = nextTwos;
    }
    return ones;
  }
}
