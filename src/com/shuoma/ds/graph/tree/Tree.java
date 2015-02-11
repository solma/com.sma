package com.shuoma.ds.graph.tree;

import com.shuoma.ds.graph.basic.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
  public HashMap<String, Node> treeNodes;

  public Node root;

  public Tree(Node root) {
    this.root = root;
    treeNodes = new HashMap<>();
    treeNodes.put(root.getId(), root);
  }

  public enum TRAVERSAL_ORDER {
    PREORDER, POSTORDER;
  }

  public ArrayList<ArrayList<Node>> traverse(TRAVERSAL_ORDER order) {
    ArrayList<Node> treeNodes = new ArrayList<>();
    if (root != null) {
      switch (order) {
        case PREORDER:
          traverse(root, treeNodes, TRAVERSAL_ORDER.PREORDER);
          break;
        case POSTORDER:
          traverse(root, treeNodes, TRAVERSAL_ORDER.PREORDER);
          break;
        default:
          throw new IllegalArgumentException();
      }
    }
    ArrayList<ArrayList<Node>> allPaths = new ArrayList<ArrayList<Node>>();
    allPaths.add(treeNodes);
    return allPaths;
  }

  public ArrayList<Node> traverse(Node start, ArrayList<Node> treeNodes, TRAVERSAL_ORDER order) {
    if (start != null) {
      switch (order) {
        case PREORDER:
          treeNodes.add(start);
          for (Node child : start.getAdjacentNodes())
            traverse(child, treeNodes, TRAVERSAL_ORDER.PREORDER);
          break;
        case POSTORDER:
          for (Node child : start.getAdjacentNodes())
            traverse(child, treeNodes, TRAVERSAL_ORDER.PREORDER);
          treeNodes.add(start);
          break;
        default:
          throw new IllegalArgumentException();
      }
    }
    return treeNodes;
  }
}
