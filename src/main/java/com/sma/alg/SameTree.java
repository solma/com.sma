package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(dss = BinarySearchTree, references = LeetCode)
public class SameTree {
  //second pass
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null)
      return true;
    else {
      if (p != null && q != null)
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
      else
        return false;
    }
  }

  //first pass
  public boolean isSameTreeFirstPass(TreeNode p, TreeNode q) {
    if (p == null && q == null)
      return true;
    if (p != null && q != null && p.val == q.val)
      return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    return false;
  }
}
