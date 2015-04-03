package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = BinaryTree, source = LeetCode)
public class MinDepthLeafBinaryTree {
  //second pass
  public int minDepth(TreeNode root) {
    if (root == null)
      return 0;
    if (root.left == null && root.right == null)
      return 1;
    int depth = Integer.MAX_VALUE;
    if (root.left != null)
      depth = minDepth(root.left);
    if (root.right != null)
      depth = Math.min(depth, minDepth(root.right));
    return depth + 1;
  }

  //first pass
  public int minDepthFirstPass(TreeNode root) {
    if (root == null)
      return 0;
    int[] minDepth = new int[] {Integer.MAX_VALUE};
    minDepth(root, minDepth, 1);
    return minDepth[0];
  }

  public void minDepth(TreeNode n, int[] minDepth, int curDepth) {
    if (n.left == null && n.right == null && curDepth < minDepth[0])
      minDepth[0] = curDepth;
    if (n.left != null)
      minDepth(n.left, minDepth, curDepth + 1);
    if (n.right != null)
      minDepth(n.right, minDepth, curDepth + 1);
  }
}
