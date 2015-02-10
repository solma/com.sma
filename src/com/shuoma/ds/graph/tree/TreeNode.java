package com.shuoma.ds.graph.tree;

import com.shuoma.ds.graph.Node;

import java.util.ArrayList;

public class TreeNode extends Node {
  public ArrayList<TreeNode> children;

  public TreeNode(int i) {
    this(new Node(i));
  }

  public TreeNode(Node n) {
    id = n.id;
    status = n.status;
    value = n.value;
    dis = n.dis;

    children = new ArrayList<>();
    /*
     * for(Edge e:n.adjacentList){ children.add(new TreeNode(e.opposite(n)) ); }
     */
  }

  @Override
  public String toString() {
    return id;
  }
}
