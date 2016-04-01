package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(dss = BinaryTree, references = LeetCode)
public class BinaryTreePreorderTraversal {
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ret = new ArrayList<>();
    if (root == null)
      return ret;
    ret.add(root.val);
    ret.addAll(preorderTraversal(root.left));
    ret.addAll(preorderTraversal(root.right));
    return ret;
  }
}
