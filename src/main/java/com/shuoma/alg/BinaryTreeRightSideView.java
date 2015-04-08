package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.DataStructure.Queue;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@Tag(dss = {BinaryTree, Queue}, source = LeetCode)
public class BinaryTreeRightSideView {

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ret = new LinkedList<>();
    if (root == null) {
      return ret;
    }

    Deque<TreeNode> nextLevel = new LinkedList<>();
    nextLevel.add(root);
    while (!nextLevel.isEmpty()) {
      Deque<TreeNode> curLevel = nextLevel;
      ret.add(curLevel.getLast().val);
      nextLevel = new LinkedList<>();
      while (!curLevel.isEmpty()) {
        TreeNode cur = nextLevel.poll();
        if (cur.left != null) {
          curLevel.add(cur.left);
        }
        if (cur.right != null) {
          curLevel.add(cur.right);
        }
      }
      nextLevel = curLevel;
    }
    return ret;
  }
}
