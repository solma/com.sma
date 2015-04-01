package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Recursion, dss = BinaryTree, source = LeetCode)
public class SymmetricTree {
  //second pass
  public boolean isSymmetric(TreeNode root) {
    return isSameTree(root, mirror(root));
  }

  public TreeNode mirror(TreeNode node) {
    if (node == null)
      return null;
    TreeNode mirror = new TreeNode(node.val);
    mirror.right = mirror(node.left);
    mirror.left = mirror(node.right);
    return mirror;
  }

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
  public boolean isSymmetricFirstPass(TreeNode root) {
    if (root == null)
      return true;
    TreeNode nt = new TreeNode(root.val);
    getSymmetricTree(root, nt);
    return isSameTree(root, nt);
  }

  public void getSymmetricTree(TreeNode root, TreeNode nt) {
    if (root.left != null) {
      nt.right = new TreeNode(root.left.val);
      getSymmetricTree(root.left, nt.right);
    }
    if (root.right != null) {
      nt.left = new TreeNode(root.right.val);
      getSymmetricTree(root.right, nt.left);
    }
  }

  public boolean isSameTreeFirstPass(TreeNode t1, TreeNode t2) {
    if ((t1 != null && t2 != null && t1.val == t2.val) || t1 == null && t2 == null) {
      if (t1 == null)
        return true;
      return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }
    return false;
  }
}
