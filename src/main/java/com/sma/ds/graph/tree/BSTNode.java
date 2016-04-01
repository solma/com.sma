package com.sma.ds.graph.tree;

/** Binary Search Tree Node. */
public class BSTNode {
  public String id;
  public BSTNode left;
  public BSTNode right;
  BSTNode next;
  /** value for customized semantics */
  public int value;
  /** size of the its subtree. */
  public int size;

  public BSTNode(BSTNode copy) {
    this.id = copy.id;
    this.value = copy.value;
    this.left = copy.left;
    this.right = copy.right;
  }

  public BSTNode(String key, int value) {
    this.id = key;
    this.value = value;
    left = null;
    right = null;
    next = null;
  }

  public BSTNode(String key) {
    this(key, 0);
  }

  @Override public String toString() {
    return "(" + id + ", " + value + ")";
  }
}
