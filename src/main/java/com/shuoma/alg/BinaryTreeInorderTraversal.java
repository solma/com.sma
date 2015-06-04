package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;

@Tag(dss = BinaryTree, reference = LeetCode)
public class BinaryTreeInorderTraversal {

  public ArrayList<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> ret = new ArrayList<>();
    if (root == null) {
      return ret;
    }
    ret.addAll(inorderTraversal(root.left));
    ret.add(root.val);
    ret.addAll(inorderTraversal(root.right));
    return ret;
  }
}
