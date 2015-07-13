package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = BinaryTree, references = LeetCode)
public class LowestCommonAncestorOfBinaryTree {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    if (root == p || root == q) {
      return root;
    }
    TreeNode leftRet = lowestCommonAncestor(root.left, p, q);
    TreeNode rightRet = lowestCommonAncestor(root.right, p, q);
    return (leftRet != null && rightRet != null) ? root : (leftRet != null ? leftRet : rightRet);
  }
}
