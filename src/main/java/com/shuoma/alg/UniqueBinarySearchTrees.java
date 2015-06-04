package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Recursion, dss = BinarySearchTree, reference = LeetCode)
public class UniqueBinarySearchTrees {
  public int numTrees(int n) {
    int sum = 0;
    if (n == 1 || n == 0)
      return 1;
    for (int i = 1; i <= n; i++) {
      sum += numTrees(i - 1) * numTrees(n - i);
    }
    return sum;
  }
}
