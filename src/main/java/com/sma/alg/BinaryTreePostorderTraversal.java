package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(dss = BinaryTree, references = LeetCode)
public class BinaryTreePostorderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ret = new ArrayList<>();
    if (root == null)
      return ret;
    ret.addAll(postorderTraversal(root.left));
    ret.addAll(postorderTraversal(root.right));
    ret.add(root.val);
    return ret;
  }
}
