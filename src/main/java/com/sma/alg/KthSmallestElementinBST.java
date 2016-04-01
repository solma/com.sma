package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(dl = D2, dss = BinarySearchTree, references = LeetCode)
public class KthSmallestElementinBST {
  public int kthSmallest(TreeNode root, int k) {
    Map<TreeNode, Integer> nodeSize = new HashMap<>();
    getAllNodeSize(root, nodeSize);
    return kthSmallest(root, k, nodeSize).val;
  }

  TreeNode kthSmallest(TreeNode cur, int k, Map<TreeNode, Integer> nodeSize) {
    int leftSize = cur.left == null ? 0 : nodeSize.get(cur.left);
    if (leftSize == k - 1) { return cur; }
    if (leftSize >= k) { return kthSmallest(cur.left, k, nodeSize); }
    return kthSmallest(cur.right, k - leftSize - 1, nodeSize);
  }

  void getAllNodeSize(TreeNode cur, Map<TreeNode, Integer> nodeSize) {
    if (cur == null) return;
    getAllNodeSize(cur.left, nodeSize);
    getAllNodeSize(cur.right, nodeSize);
    int leftSize = cur.left == null ? 0 : nodeSize.get(cur.left);
    int rightSize = cur.right == null ? 0 : nodeSize.get(cur.right);
    nodeSize.put(cur, leftSize + rightSize + 1);
  }
}
