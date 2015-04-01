package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Recursion, dss = BinaryTree, source = LeetCode)
public class ValidBST {
  //second pass
  public boolean isValidBST(TreeNode root) {
    if (root == null)
      return true;
    double[] minAndMax = isBST(root);
    return minAndMax == null ? false : true;
  }

  private double[] isBST(TreeNode node) {

    double[] range = {node.val, node.val};
    if (node.left != null) {
      double[] rangeLeft = isBST(node.left);
      if (rangeLeft == null || rangeLeft[1] >= node.val)
        return null;
      range[0] = rangeLeft[0];
    }

    if (node.right != null) {
      double[] rangeRight = isBST(node.right);
      if (rangeRight == null || node.val >= rangeRight[0])
        return null;
      range[1] = rangeRight[1];
    }
    return range;
  }


  //first pass
  public boolean isValidBSTFirstPass(TreeNode root) {
    // Start typing your Java solution below
    // DO NOT write main() function
    return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public boolean isValidBST(TreeNode cur, int lower, int upper) {
    if (cur == null)
      return true;
    if (cur.val < lower || cur.val > upper)
      return false;
    return isValidBST(cur.left, lower, cur.val - 1) && isValidBST(cur.right, cur.val + 1, upper);
  }
}
