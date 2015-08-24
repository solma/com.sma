package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Recursion, dss = BinaryTree, references = LeetCode)
public class SumRootToLeafNumbers {
  public int sumNumbers(TreeNode root) {
    if (root == null) { return 0; }
    int[] sum = new int[2];
    StringBuilder sb = new StringBuilder();
    topdown(root, sb, sum);
    return sum[0];
  }

  private void topdown(TreeNode node, StringBuilder sb, int[] sum) {
    sb.append(String.valueOf(node.val));
    if (node.left != null) {
      topdown(node.left, sb, sum);
      sb = sb.deleteCharAt(sb.length() - 1);
    }
    if (node.right != null) {
      topdown(node.right, sb, sum);
      sb = sb.deleteCharAt(sb.length() - 1);
    }
    if (node.left == null && node.right == null) {
      sum[0] += Integer.parseInt(sb.toString());
      return;
    }
  }
}
