package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.CheckAtEveryIndex;

import com.shuoma.annotation.Tag;

@Tag(algs = Recursion, dss = BinaryTree, references = LeetCode, tricks = CheckAtEveryIndex)
public class BinaryTreeMaximumPathSum {

  public int maxPathSum(TreeNode root) {
    if (root == null) { return 0; }
    int[] max = new int[] {Integer.MIN_VALUE};
    maxPathSum(root, max);
    return max[0];
  }

  public int maxPathSum(TreeNode cur, int[] max) {
    if (cur == null) { return 0; }

    int leftMax = Math.max(0, maxPathSum(cur.left, max));
    int rightMax = Math.max(0, maxPathSum(cur.right, max));

    // update max with a path including cur node
    max[0] = Math.max(max[0], cur.val + leftMax + rightMax);

    // return the max sum of any path ending with cur node
    return Math.max(cur.val + leftMax, cur.val + rightMax);
  }
}
