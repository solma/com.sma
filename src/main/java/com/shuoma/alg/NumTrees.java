package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Recursion, reference = LeetCode)
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
