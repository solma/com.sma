package com.shuoma.alg;
//Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
//For example:
//Given a binary tree {1,2,3,4,5},
//    1
//   / \
//  2   3
// / \
//4   5
//return the root of the binary tree [4,5,2,#,#,3,1].
//   4
//  / \
// 5   2
//    / \
//   3   1

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = BinaryTree, reference = LeetCode)
public class BinaryTreeUpsideDown {
  public TreeNode reverseRecursively(TreeNode root) {
    TreeNode left = root.left;
    TreeNode newRoot = reverseRecursively(left);
    left.left = root.right;
    left.right = root;
    root.left = root.right = null;
    return newRoot;
  }

  public TreeNode reverseIteratively(TreeNode root) {
    TreeNode cur = root, parent = null, rightSibling = null, leftTemp;
    while (cur != null) {
      leftTemp = cur.left;
      cur.left = rightSibling;
      cur.right = parent;
      parent = cur;
      rightSibling = parent.right;
      cur = leftTemp;
    }
    return parent;
  }
}
