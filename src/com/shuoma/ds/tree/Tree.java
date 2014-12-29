package com.shuoma.ds.tree;

import com.shuoma.ds.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
  public HashMap<String, TreeNode> treeNodes;

  public TreeNode root;

  public Tree(TreeNode root) {
    this.root = root;
    treeNodes = new HashMap<String, TreeNode>();
    treeNodes.put(root.id, root);
  }

  public enum TRAVERSAL_ORDER {
    PREORDER, POSTORDER;
  }

  /**
   *
   * @param start:
   * @param order: one of the values defined in Tree.TRAVERSAL_ORDER
   * @return
   */
  public ArrayList<ArrayList<Node>> traverse(TRAVERSAL_ORDER order) {
    ArrayList<Node> treeNodes = new ArrayList<Node>();
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

  public ArrayList<Node> traverse(TreeNode start, ArrayList<Node> treeNodes, TRAVERSAL_ORDER order) {
    if (start != null) {
      switch (order) {
        case PREORDER:
          treeNodes.add(start);
          for (TreeNode child : start.children)
            traverse(child, treeNodes, TRAVERSAL_ORDER.PREORDER);
          break;
        case POSTORDER:
          for (TreeNode child : start.children)
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
