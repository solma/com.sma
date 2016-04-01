package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = Recursion, references = LeetCode)
public class NumTrees {
  public int numTrees(int n) {
    if (n == 0 || n == 1)
      return 1;
    int sum = 0;
    for (int i = 1; i <= n; i++) {
      sum += numTrees(n - i) * numTrees(i - 1);
    }
    return sum;

  }
}
