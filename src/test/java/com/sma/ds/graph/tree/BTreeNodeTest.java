package com.sma.ds.graph.tree;

import org.junit.Test;

public class BTreeNodeTest {

  @Test public void testIsValid() throws Exception {
    int[][] btree = {
        {-1, 7, 16},
        {0, 1, 2, 5, 6},
        {0, 9, 12},
        {0, 18, 21},
        {3, 17},
        {3, 19},
        {3, 22, 25}
    };
    BTreeNode root = BTreeNode.buildBTree(btree);
    System.out.println(root.isValid());
  }
}
