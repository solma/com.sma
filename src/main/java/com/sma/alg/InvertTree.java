package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.LinkedList;
import java.util.Queue;

@Tag(dss = BinaryTree, references = LeetCode)
public class InvertTree {

  // iterative
  public TreeNode invertTreeI(TreeNode root) {
    if (root == null) { return null; }
    // alternatively queue, does not matter
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    // top-down
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      TreeNode tmp = cur.left;
      cur.left = cur.right;
      cur.right = tmp;
      if (cur.left != null) queue.add(cur.left);
      if (cur.right != null) queue.add(cur.right);
    }
    return root;
  }

  // bottom up recursion
  public TreeNode invertTreeR(TreeNode root) {
    if (root == null) { return null; }
    TreeNode leftInvert = invertTreeR(root.left);
    TreeNode rightInvert = invertTreeR(root.right);
    root.left = rightInvert;
    root.right = leftInvert;
    return root;
  }
}
