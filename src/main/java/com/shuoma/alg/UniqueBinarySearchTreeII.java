package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = Recursion, dss = BinarySearchTree, references = LeetCode)
public class UniqueBinarySearchTreeII {
  public List<TreeNode> generateTrees(int n) {
    return generateTrees(1, n);
  }

  public List<TreeNode> generateTrees(int smallest, int largest) {
    ArrayList<TreeNode> allTrees = new ArrayList<TreeNode>();
    for (int i = smallest; i <= largest; i++) {
      for (TreeNode leftSubtree : generateTrees(smallest, i - 1)) {
        for (TreeNode rightSubtree : generateTrees(i + 1, largest)) {
          TreeNode newTree = new TreeNode(i);
          newTree.left = leftSubtree;
          newTree.right = rightSubtree;
          allTrees.add(newTree);
        }
      }
    }
    if (allTrees.size() == 0)
      allTrees.add(null);
    return allTrees;
  }
}
