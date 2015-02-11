package com.shuoma.ds.graph.tree;

import com.shuoma.ds.graph.basic.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeNode extends Node {
  private List<TreeNode> children;

  public TreeNode(Node n) {
    super(n);
    aliasAdjcentNodes(n.getAdjacentNodes());
  }

  public TreeNode(int i) {
    this(new Node(String.valueOf(i), i));
  }

  public void addChild(TreeNode child) {
    children.add(child);
  }

  public void aliasAdjcentNodes(List<Node> adjecentNodes) {
    children = new ArrayList<>(adjecentNodes.size());
    for (Node node : adjecentNodes) {
      children.add((TreeNode)node);
    }
  }

  public List<TreeNode> getChildren() {
    return children;
  }
}
