package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;

import com.shuoma.annotation.Tag;

/**
 * // Given a string of nested ternary operations, such as a?b?c:d:e, it denote the tree like following:
 * // root is a, a.left is the subtree of b?c:d, and a.right is e
 * //      a
 * //    b   e
 * //  c   d
 * // Write code to build the tree based on given string.
 */

@Tag(algs = Recursion, dss = BinaryTree)
public class TreeTernaryRepresentation {

  static final char LEFT = '?';

  public static void main(String[] args) {
    TreeNode tree = fromTernaryString("a?b?d:e:c");
    System.out.println(tree);
  }

  static TreeNode fromTernaryString(String s) {
    return buildTreeR(s);
  }

  static int index = 0;
  static TreeNode buildTreeR(String str) {
    if (index >= str.length()) { return null; }
    TreeNode node = new TreeNode(str.charAt(index++));
    if(index == str.length() || str.charAt(index) != LEFT) { return node; }
    index++;
    node.left = buildTreeR(str);
    index++;
    node.right = buildTreeR(str);
    return node;
  }
}
