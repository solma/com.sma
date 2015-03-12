package com.shuoma.ds.graph.tree;

import java.util.HashMap;

public class Tree {
  public HashMap<String, TreeNode> treeNodes;

  public TreeNode root;

  public Tree(TreeNode root) {
    this.root = root;
    treeNodes = new HashMap<>();
    treeNodes.put(root.getId(), root);
  }
}
