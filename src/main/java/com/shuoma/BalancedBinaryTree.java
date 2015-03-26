package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;

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

@Tag(dss = BinaryTree)
public class BalancedBinaryTree {

  public boolean isBalanced(TreeNode root) {
    return height(root) != -1;
  }

  private int height(TreeNode root) {
    if (root == null)
      return 0;

    int leftHeight = height(root.left);
    if (leftHeight == -1)
      return -1;

    int rightHeight = height(root.right);
    if (rightHeight == -1)
      return -1;

    if (Math.abs(leftHeight - rightHeight) > 1)
      return -1;

    return 1 + Math.max(leftHeight, rightHeight);
  }
}
