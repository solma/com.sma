package com.shuoma.ds.tree;

import com.shuoma.ds.tree.BST.BSTNode;
// ref:http://www.mitbbs.com/article_t/JobHunting/32695977.html

// Given a binary tree where all the right nodes are either empty or leaf
// nodes, flip it upside down
// and turn it into a tree with left leaf nodes.
// * In the original tree, if a node has a right child, it also must have a
// left child.

// * 1 1
// * / \ /\
// * 2 3 2 3
// * /
// * 4
// * / \
// * 5 6

// Will be translated into:
// * 1 1
// * / /
// * 2---3 2---3
// * /
// * 4
// * /
// * 5---6
// *
// Finally:
// * 5 2
// * / \ / \
// * 6 4 3 1
// * \
// * 2
// * / \
// * 3 1


public class FlipTree {

  BSTNode convert(BSTNode root) {
    if (root == null || root.left == null) return root;

    BSTNode left = root.left;
    BSTNode newRoot = convert(left);
    left.right = root;
    left.left = root.right;
    root.left = root.right = null;

    return newRoot;
  }
}
