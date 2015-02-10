package com.shuoma.alg.recursion;

import com.shuoma.ds.graph.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a tree, find its max independent set.
 */
public class TreeMaxIndependentSet {
  public static void main(String[] args) {
    int n = 14;
    TreeNode[] nodes = new TreeNode[n];
    for(int i = 1; i < nodes.length; i++)
      nodes[i] = new TreeNode(i);

    nodes[1].children.add(nodes[2]);
    nodes[1].children.add(nodes[3]);
    nodes[1].children.add(nodes[4]);
    nodes[3].children.add(nodes[5]);
    nodes[3].children.add(nodes[6]);
    nodes[3].children.add(nodes[7]);
    nodes[6].children.add(nodes[8]);
    nodes[6].children.add(nodes[9]);
    nodes[7].children.add(nodes[10]);
    nodes[7].children.add(nodes[11]);
    nodes[10].children.add(nodes[12]);
    nodes[10].children.add(nodes[13]);

    //System.out.println(nodes[1].value);
    System.out.println(maxIndependentSet(nodes[1]));
  }

  static Map<String, Double> memory = new HashMap<>();

  static double maxIndependentSet(TreeNode root) {
    return Math.max(maxIndependentSet(root, true), maxIndependentSet(root, false));
  }

  static double maxIndependentSet(TreeNode cur, boolean selected) {
    if (cur == null)
      return 0;
    String key = cur.value + "" + selected;
    if (memory.containsKey(key))
      return memory.get(key);

    double sum;
    if (selected) {
      sum = cur.value;
      for (TreeNode child : cur.children) {
        sum += maxIndependentSet(child, false);
      }
      memory.put(key, sum);
      return sum;
    }

    sum = 0;
    for (TreeNode child : cur.children) {
      sum += maxIndependentSet(child);
    }
    memory.put(key, sum);
    return sum;
  }
}
