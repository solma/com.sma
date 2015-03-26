package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.DataStructure.Queue;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(dss = {BinaryTree, Queue}, source = LeetCode)
public class BinaryTreeLevelOrderTraversal {
  public List<ArrayList<Integer>> levelOrder(TreeNode root) {
    List<ArrayList<Integer>> ret = new ArrayList<>();
    if (root == null) {
      return ret;
    }
    List<TreeNode> currentLvl;
    List<TreeNode> nextLvl = new ArrayList<>();
    nextLvl.add(root);
    while (!nextLvl.isEmpty()) {
      currentLvl = nextLvl;
      nextLvl = new ArrayList<>();
      ArrayList<Integer> lvl = new ArrayList<>();
      while (!currentLvl.isEmpty()) {
        TreeNode cur = currentLvl.remove(0);
        lvl.add(cur.val);
        //System.out.print(cur+"\t");
        if (cur.left != null)
          nextLvl.add(cur.left);
        if (cur.right != null)
          nextLvl.add(cur.right);
      }
      ret.add(lvl);
    }
    return ret;
  }
}
