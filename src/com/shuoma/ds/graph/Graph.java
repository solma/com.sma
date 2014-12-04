package com.shuoma.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Graph {
  public HashMap<String, Node> nodes;

  public void reset() {
    for (Node node : nodes.values())
      node.reset();
  }

  public ArrayList<ArrayList<Node>> buildAllPaths(Node start, Node cur, ArrayList<Node> path) {
    ArrayList<ArrayList<Node>> allPaths = new ArrayList<ArrayList<Node>>();
    if (cur.equals(start)) {
      allPaths.add(new ArrayList<Node>(path));
    }
    for (Node prev : cur.prevs) {
      path.add(0, prev);
      allPaths.addAll(buildAllPaths(start, prev, path));
      path.remove(0);
    }
    return allPaths;
  }

  public void printPath(ArrayList<ArrayList<Node>> paths) {
    printPath(paths, false);
  }

  public void printPath(ArrayList<ArrayList<Node>> paths, boolean byValue) {
    System.out.println("All Paths:");
    for (ArrayList<Node> path : paths) {
      for (int i = 0; i < path.size(); i++) {
        if (byValue)
          System.out.print(path.get(i).value);
        else
          System.out.print(path.get(i));
        if (i < path.size() - 1) System.out.print(" --> ");
      }

      System.out.println();
    }
  }
}
