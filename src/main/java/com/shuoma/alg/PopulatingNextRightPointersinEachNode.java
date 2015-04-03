package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;

@Tag(dss = BinaryTree, source = LeetCode)
public class PopulatingNextRightPointersinEachNode {
  //constant space
  public void connect(TreeLinkNode root) {
    while (root != null) {
      TreeLinkNode nextLev = null;
      TreeLinkNode prev = null;
      for (; root != null; root = root.next) {
        if (nextLev == null)
          nextLev = root.left != null ? root.left : root.right;
        if (root.left != null) {
          if (prev != null)
            prev.next = root.left;
          prev = root.left;
        }
        if (root.right != null) {
          if (prev != null)
            prev.next = root.right;
          prev = root.right;
        }
      }
      root = nextLev;
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
      nextLayer = new ArrayList<TreeLinkNode>();
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
