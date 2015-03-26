package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

@Tag(algs = Recursion, dl = D2, dss = BinaryTree, source = LeetCode)
public class BinaryTreeMaximumPathSum {

  public int maxPathSum(TreeNode root) {
    if (root == null)
      return 0;
    int[] max = new int[] {Integer.MIN_VALUE};
    maxPathSum(root, max);
    return max[0];
  }

  public int maxPathSum(TreeNode n, int[] max) {
    if (n == null)
      return 0;

    int leftMax = Math.max(0, maxPathSum(n.left, max));
    int rightMax = Math.max(0, maxPathSum(n.right, max));

    if (n.val + leftMax + rightMax > max[0])
      max[0] = n.val + leftMax + rightMax;
    // return the max of the following:
    // 1. the left path sum or 2. right path sum 3. the value it self
    return Math.max(n.val + leftMax, n.val + rightMax);
  }
}
