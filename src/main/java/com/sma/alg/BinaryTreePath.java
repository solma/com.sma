package com.sma.alg;


import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 */
@Tag(dss = BinaryTree, references = LeetCode)
public class BinaryTreePath {
  List<String> binaryTreePaths(TreeNode cur) {
    List<String> allPaths = new LinkedList<>();
    if (cur == null) return allPaths;
    if (cur.left == null && cur.right == null) {
      allPaths.add("" + cur.val);
      return allPaths;
    }
    for (String path: binaryTreePaths(cur.left)) {
      allPaths.add(cur.val + "->" + path);
    }
    for (String path: binaryTreePaths(cur.right)) {
      allPaths.add(cur.val + "->" + path);
    }
    return allPaths;
  }
}
