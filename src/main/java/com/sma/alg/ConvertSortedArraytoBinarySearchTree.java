package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.Arrays;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
@Tag(dl = D2, dss = BinaryTree, references = LeetCode)
public class ConvertSortedArraytoBinarySearchTree {
  //second pass
  public TreeNode sortedArrayToBST(int[] num) {
    if (num == null)
      return null;
    int n = num.length;
    if (n == 0)
      return null;
    int m = n / 2;
    TreeNode root = new TreeNode(num[m]);
    root.left = sortedArrayToBST(Arrays.copyOfRange(num, 0, m));
    root.right = sortedArrayToBST(Arrays.copyOfRange(num, m + 1, n));
    return root;
  }

  //first pass
  public TreeNode sortedArrayToBSTFirstPass(int[] num) {
    int n = num.length;
    if (n == 0)
      return null;
    int mid = n / 2;
    TreeNode root = new TreeNode(num[mid]);
    root.right = sortedArrayToBST(Arrays.copyOfRange(num, mid + 1, n));
    root.left = sortedArrayToBST(Arrays.copyOfRange(num, 0, mid));
    return root;
  }
}
