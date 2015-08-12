package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;

@Tag(dss = BinaryTree, references = LeetCode)
public class PopulatingNextRightPointersinEachNode {
  //constant space
  public void connect(TreeLinkNode cur) {
    while (cur != null) {
      TreeLinkNode nextLev = null, prev = null;
      for (; cur != null; cur = cur.next) {
        if (nextLev == null) {
          nextLev = cur.left != null ? cur.left : cur.right;
        }
        if (cur.left != null) {
          if (prev != null)
            prev.next = cur.left;
          prev = cur.left;
        }
        if (cur.right != null) {
          if (prev != null)
            prev.next = cur.right;
          prev = cur.right;
        }
      }
      cur = nextLev;
    }
  }

  //not constant space
  public void connectNonConstantSpace(TreeLinkNode root) {
    if (root == null)
      return;
    ArrayList<TreeLinkNode> curLayer = new ArrayList<TreeLinkNode>();
    curLayer.add(root);

    TreeLinkNode pre;
    ArrayList<TreeLinkNode> nextLayer;
    while (!curLayer.isEmpty()) {
      pre = null;
      nextLayer = new ArrayList<>();
      while (!curLayer.isEmpty()) {
        TreeLinkNode cur = curLayer.remove(0);
        if (pre != null)
          pre.next = cur;
        pre = cur;
        if (cur.left != null)
          nextLayer.add(cur.left);
        if (cur.right != null)
          nextLayer.add(cur.right);
      }
      curLayer = nextLayer;
    }
  }
}
