package com.shuoma.alg;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  TreeNode(int x) {
    val = x;
  }

  public String toString() {
    return val + " " + String.valueOf(left != null ? left.val : -1) + " " + String
        .valueOf(right != null ? right.val : -1);
  }
}
