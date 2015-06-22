package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.Queue;

@Tag(dss = BinaryTree, references = LeetCode)
public class InvertTree {

  // iterative
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    // alternatively queue, does not matter
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
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

  // recurison
  public TreeNode invertTreeRecursion(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode tmp = root.left;
    root.left = root.right;
    root.right = tmp;
    invertTreeRecursion(root.left);
    invertTreeRecursion(root.right);
    return root;
  }
}
