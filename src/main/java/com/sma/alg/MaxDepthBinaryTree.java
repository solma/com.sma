package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(dss = BinaryTree, references = LeetCode)
public class MaxDepthBinaryTree {
  //second pass
  public int maxDepth(TreeNode root) {
    if (root == null)
      return 0;
    int depth = 0;
    if (root.left != null)
      depth = maxDepth(root.left);
    if (root.right != null)
      depth = Math.max(depth, maxDepth(root.right));
    return depth + 1;
  }

  //first pass
  public int maxDepthFirstPass(TreeNode root) {
    if (root == null)
      return 0;
    int[] maxDepth = new int[] {0};
    maxDepth(root, maxDepth, 1);
    return maxDepth[0];
  }

  public void maxDepth(TreeNode n, int[] maxDepth, int curDepth) {
    if (n.left == null && n.right == null && curDepth > maxDepth[0])
      maxDepth[0] = curDepth;
    if (n.left != null)
      maxDepth(n.left, maxDepth, curDepth + 1);
    if (n.right != null)
      maxDepth(n.right, maxDepth, curDepth + 1);
  }
}
