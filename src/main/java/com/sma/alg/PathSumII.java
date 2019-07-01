package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(algs = Recursion, dl = D2, dss = BinaryTree, references = LeetCode)
public class PathSumII {

  //second pass
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> ret = new ArrayList<>();
    if (root == null) return ret;
    if (root.val == sum && root.left == null && root.right == null) {
      List<Integer> onePath = Arrays.asList(root.val);
      ret.add(onePath);
      return ret;
    }
    if (root.left != null) {
      List<List<Integer>> left = pathSum(root.left, sum - root.val);
      for (List<Integer> path : left) {
        path.add(0, root.val);
      }
      ret.addAll(left);
    }
    if (root.right != null) {
      List<List<Integer>> right = pathSum(root.right, sum - root.val);
      for (List<Integer> path : right) {
        path.add(0, root.val);
      }
      ret.addAll(right);
    }
    return ret;
  }


  public List<List<Integer>> pathSumFirstPass(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList<>();
    if (root != null)
      pathSum(root, sum, new ArrayList<>(), res);
    return res;
  }

  public void pathSum(TreeNode node, int target, List<Integer> path, List<List<Integer>> res) {
    target -= node.val;
    path.add(node.val);
    if (node.left == null && node.right == null) {
      if (target == 0)
        res.add(new ArrayList<>(path));
      return;
    }
    if (node.left != null) {
      pathSum(node.left, target, path, res);
      path.remove(path.size() - 1);
    }
    if (node.right != null) {
      pathSum(node.right, target, path, res);
      path.remove(path.size() - 1);
    }
  }
}
