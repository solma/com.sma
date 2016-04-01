package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;

@Tag(dss = BinaryTree, references = LeetCode)
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
