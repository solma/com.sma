package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

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
