package com.sma.ds.graph.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.sma.ds.graph.tree.BST.*;
import static java.util.Collections.reverse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BSTTest {

  /**
   *                    5
   *                   / \
   *                  3   22
   *                 /   / \
   *                1   7   25
   *                   / \
   *                  6   20
   *                     /
   *                    10
   *                      \
   *                      13
   */
  int[] nodes = {5, 22, 3, 1, 7, 20, 6, 10, 25, 13};

  @Test
  public void testPathFromChildToAncestor() throws Exception {
    BSTNode root = insert(nodes);
    BSTNode[] ancestors =new BSTNode[]{root, root.right.left};
    String[] children = new String[]{"10", "13"};
    List[] paths = new List[]{
        Arrays.asList("5", "22", "7", "20", "10"),
        Arrays.asList("7", "20", "10", "13")
    };

    for (int i = 0; i < children.length; i++) {
      reverse(paths[i]);
      assertEquals(paths[i], pathFromChildToAncestorR(ancestors[i], children[i]));
    }
  }

  @Test
  public void testLowestCommonAncestor() throws Exception {
    int[][] childAncestorPairs = {
        {5, 3, 25},
        {7, 7 ,13}
    };
    for (int[] cse : childAncestorPairs) {
      BSTNode root = insert(nodes);
      BSTNode expectedAncestor = new BSTNode(String.valueOf(cse[0]));
      BSTNode ancestor = lowestCommonAncestor(root, String.valueOf(cse[1]), String.valueOf(cse[2]));
      assertEquals(expectedAncestor.id, ancestor.id);
    }
  }

  @Test
  public void testPathBetweenTwoNodes() throws Exception {
    String[][] nodePairs = {
        {"3", "13"},
        {"6", "25"}
    };
    List[] paths = new List[]{
        Arrays.asList("3", "5", "22", "7", "20", "10", "13"),
        Arrays.asList("6", "7", "22", "25")
    };
    for (int i = 0; i < nodePairs.length; i++) {
      BSTNode root = insert(nodes);
      assertEquals(paths[i], pathBetweenTwoNodes(root, nodePairs[i][0], nodePairs[i][1]));
    }
  }

  @Test public void testDeleteAndSameTree() throws Exception {
    BSTNode root = insert(nodes);
    root = delete(root, "6");
    //prettyPrint(root);
    root = delete(root, "7");
    //prettyPrint(root);
    root = delete(root, "25");
    //prettyPrint(root);
    BSTNode afterDeletion = insert(new int[]{5, 3, 1, 22, 10, 20, 13});
    assertTrue(sameTree(root, afterDeletion));
  }

  @Test public void testCountTree() throws Exception {
    assertEquals(5, countTree(3));
  }

  @Test
  public void testMaxDistanceBtwLeaves() throws Exception {
    BSTNode root = insert(nodes);
    assertEquals(7, maxDistanceBtwLeaves(root));
  }
}
