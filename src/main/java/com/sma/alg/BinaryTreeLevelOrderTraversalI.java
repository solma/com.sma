package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.DataStructure.QueueT;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Tag(dl = D2, dss = {BinaryTree, QueueT}, references = LeetCode)
public class BinaryTreeLevelOrderTraversalI {
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> ret = new ArrayList<>();
    if (root == null) {
      return ret;
    }
    LinkedList<TreeNode> curLvl = new LinkedList<>();
    curLvl.add(root);
    LinkedList<TreeNode> nextLvl;
    while (curLvl.size() > 0) {
      nextLvl = new LinkedList<>();

      List<Integer> lvl = new ArrayList<>();
      for (TreeNode n : curLvl) {
        lvl.add(n.val);
      }
      ret.add(0, lvl);

      while (curLvl.size() > 0) {
        TreeNode cur = curLvl.poll();
        if (cur.left != null)
          nextLvl.add(cur.left);
        if (cur.right != null)
          nextLvl.add(cur.right);
      }
      curLvl = nextLvl;
    }
    return ret;
  }
}
