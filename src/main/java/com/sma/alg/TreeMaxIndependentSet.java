package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Recursion;
import static com.sma.annotation.Tag.DataStructure.BinaryTree;

import com.sma.annotation.Tag;
import com.sma.ds.graph.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

@Tag(algs = Recursion, dss = BinaryTree)
/** Given a tree, find its max independent set. */
public class TreeMaxIndependentSet {
  public static void main(String[] args) {
    int n = 14;
    TreeNode[] nodes = new TreeNode[n];
    for(int i = 1; i < nodes.length; i++)
      nodes[i] = new TreeNode(i);

    nodes[1].addChild(nodes[2]);
    nodes[1].addChild(nodes[3]);
    nodes[1].addChild(nodes[4]);
    nodes[3].addChild(nodes[5]);
    nodes[3].addChild(nodes[6]);
    nodes[3].addChild(nodes[7]);
    nodes[6].addChild(nodes[8]);
    nodes[6].addChild(nodes[9]);
    nodes[7].addChild(nodes[10]);
    nodes[7].addChild(nodes[11]);
    nodes[10].addChild(nodes[12]);
    nodes[10].addChild(nodes[13]);

    //System.out.println(nodeMap[1].value);
    System.out.println(maxIndependentSet(nodes[1]));
  }

  static Map<String, Double> memory = new HashMap<>();

  static double maxIndependentSet(TreeNode root) {
    return Math.max(maxIndependentSet(root, true), maxIndependentSet(root, false));
  }

  static double maxIndependentSet(TreeNode cur, boolean selected) {
    if (cur == null)
      return 0;
    String key = cur.getValue() + "" + selected;
    if (memory.containsKey(key))
      return memory.get(key);

    double sum;
    if (selected) {
      sum = cur.getValue();
      for (TreeNode child : cur.getChildren()) {
        sum += maxIndependentSet(child, false);
      }
      memory.put(key, sum);
      return sum;
    }

    sum = 0;
    for (TreeNode child : cur.getChildren()) {
      sum += maxIndependentSet(child);
    }
    memory.put(key, sum);
    return sum;
  }
}
