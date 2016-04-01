package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = Recursion, dss = BinarySearchTree, references = LeetCode)
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
