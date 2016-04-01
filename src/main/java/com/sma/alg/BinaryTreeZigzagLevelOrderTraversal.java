package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.BinaryTree;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.LinkedList;

@Tag(dl = D2, dss = BinaryTree, references = LeetCode)
public class BinaryTreeZigzagLevelOrderTraversal {

  //second pass
  public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    if (root == null)
      return ret;
    LinkedList<TreeNode> curLvl = new LinkedList<>();
    curLvl.add(root);
    boolean dir = true;
    while (curLvl.size() > 0) {
      LinkedList<TreeNode> nxtLvl = new LinkedList<>();
      ArrayList<Integer> layer = new ArrayList<>();
      while (curLvl.size() > 0) {
        TreeNode n = curLvl.remove(0);
        layer.add(n.val);
        if (n.left != null)
          nxtLvl.add(n.left);
        if (n.right != null)
          nxtLvl.add(n.right);
      }
      if (!dir) {//reverse the list
        int n = layer.size();
        ArrayList<Integer> cpy = new ArrayList<>(layer);
        for (int i = 0; i < layer.size(); i++)
          layer.set(i, new Integer(cpy.get(n - 1 - i)));
      }
      ret.add(layer);
      curLvl = nxtLvl;
      dir ^= true;
    }
    return ret;
  }

  //first pass
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    if (root == null)
      return ret;

    ArrayList<TreeNode> currentLvl;
    ArrayList<TreeNode> nextLvl = new ArrayList<>();
    nextLvl.add(root);
    boolean dir = false;
    while (!nextLvl.isEmpty()) {
      currentLvl = nextLvl;
      nextLvl = new ArrayList<>();

      dir ^= true;
      ArrayList<Integer> lvl = new ArrayList<>();
      if (dir) {
        for (TreeNode n : currentLvl) {
          lvl.add(n.val);
        }
      } else {
        for (int i = currentLvl.size() - 1; i >= 0; i--) {
          lvl.add(currentLvl.get(i).val);
        }
      }
      while (!currentLvl.isEmpty()) {
        TreeNode cur = currentLvl.remove(0);
        //lvl.add(cur.val);
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
