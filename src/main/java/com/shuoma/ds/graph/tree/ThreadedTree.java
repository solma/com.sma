package com.shuoma.ds.graph.tree;

//http://www.mathcs.duq.edu/drozdek/DSinJava/

//                 generic binary search threaded tree
class ThreadedTreeNode<T extends Comparable<? super T>> {
  protected T el;
  protected boolean hasSuccessor;
  protected ThreadedTreeNode<T> left, right;

  public ThreadedTreeNode() {
    left = right = null;
    hasSuccessor = false;
  }

  public ThreadedTreeNode(T el) {
    this(el, null, null);
  }

  public ThreadedTreeNode(T el, ThreadedTreeNode<T> l, ThreadedTreeNode<T> r) {
    this.el = el;
    left = l;
    right = r;
    hasSuccessor = false;
  }
}

public class ThreadedTree<T extends Comparable<? super T>> {
  private ThreadedTreeNode<T> root;

  public void insert(T el) {
    ThreadedTreeNode<T> newNode = new ThreadedTreeNode<T>(el);
    if (root == null) {              // tree is empty
      root = newNode;
      return;
    }
    ThreadedTreeNode<T> p = root, prev = null;
    while (p != null) {              // find a place to insert newNode;
      prev = p;
      if (el.compareTo(p.el) < 0)
        p = p.left;
      else if (!p.hasSuccessor)   // go to the right node only if it is
        p = p.right;           // a descendant, not a successor;
      else
        break;                 // don't follow successor link;
    }
    if (el.compareTo(prev.el) < 0) { // if newNode is left child of
      prev.left = newNode;       // its parent, the parent becomes
      newNode.hasSuccessor = true;// also its successor;
      newNode.right = prev;
    } else if (prev.hasSuccessor) {    // if parent of the newNode
      newNode.hasSuccessor = true;// is not the right-most node,
      prev.hasSuccessor = false;  // make parent's successor
      newNode.right = prev.right; // newNode's successor,
      prev.right = newNode;
    } else
      prev.right = newNode;       // otherwise has no successor;
  }

  protected void visit(ThreadedTreeNode<T> p) {
    System.out.print(p.el + " ");
  }

  protected void inorder() {
    ThreadedTreeNode<T> prev, p = root;
    if (p == null) {
      return;
    }
    // process only non-empty trees;
    while (p.left != null)       // go to the leftmost node;
      p = p.left;
    while (p != null) {
      visit(p);
      prev = p;
      p = p.right;             // go to the right node and only
      if (p == null || prev.hasSuccessor) continue;
      // if it is a descendant
      while (p.left != null)          // go to the leftmost node,
        p = p.left;      // otherwise visit the successor;
    }
  }

  protected void preorder() {
    ThreadedTreeNode<T> p = root;
    while (p != null) {              // process only non-empty trees;
      visit(p);
      if (p.left != null)
        p = p.left;
      else if (p.right != null && !p.hasSuccessor)
        p = p.right;
      else {                       // if p is a leaf, go through the chain
        while (p.hasSuccessor)  // of its
          p = p.right;        // (already visited) inorder successors
        p = p.right;            // and restart with the right descendant
      }                            // of the last successor;
    }
  }

  protected void postorder() {
    ThreadedTreeNode<T> q, r, s, p = new ThreadedTreeNode<T>(),
        rightmost, dummy = p;
    p.left = root;
    for (rightmost = root; rightmost.right != null; rightmost = rightmost.right);
    rightmost.hasSuccessor = true;
    rightmost.right = p;
    final int goLeft = 1, goRight = 2, visiting = 3;
    int dir = goLeft;
    while (p != null) {              // process only non-empty trees;
      if (dir == goLeft)
        if (p.left != null)
          p = p.left;
        else
          dir = goRight;
      else if (dir == goRight)
        if (p.right != null && !p.hasSuccessor) {
          p = p.right;
          dir = goLeft;
        } else
          dir = visiting;
      else {
        if (p == dummy) {
          rightmost.right = null;  // restore original setting
          rightmost.hasSuccessor = false; // of rightmost node;
          return;
        }
        visit(p);
        if (p.right != null && p.right.left == p) { // parent == successor;
          p = p.right;
          dir = goRight;
        } else {
          // scan a right-extended chain of nodes and
          // reverse right pointers;
          for (q = p.right.left, r = q.right, s = r.right; r != p; q = r, r = s, s = s.right)
            r.right = q;
          // scan the chain backwards, visit each node, and
          // restore the original setting of right pointers;
          for (s = q.right; q != p.right.left; q.right = r, r = q, q = s, s = s.right)
            visit(q);
          visit(q);
          p = p.right;
          dir = goRight;
        }
      }
    }
  }
}
