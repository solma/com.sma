package com.shuoma;
import java.util.Stack;

public class BSTIterator {

  Stack<TreeNode> stck;

  public BSTIterator(TreeNode root) {
    stck = new Stack<>();
    while (root != null) {
      stck.push(root);
      root = root.left;
    }
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stck.isEmpty();
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode top = stck.pop();
    if (top.right != null) {
      TreeNode next = top.right;
      while (next != null) {
        stck.push(next);
        next = next.left;
      }
    }
    return top.val;
  }
}

/**
 * Your BSTIterator will be called like this: BSTIterator i = new BSTIterator(root); while
 * (i.hasNext()) v[f()] = i.next();
 */
