package com.shuoma.ds.graph;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
  public ArrayList<Edge> adjacentList;
  public String id;
  /**
   * Shortest path aglorithm
   */
  public double value;
  public double dis;


  public Node() {}

  public Node(int val) {
    this.value = val;
  }

  /**
   * Fields for traversal algorithm
   */
  public static enum STATUS {
    UNVISITED, VISITED, EXPANED;
  }

  public STATUS status;
  public List<Node> prevs;

  public void reset() {
    status = STATUS.UNVISITED;
    for (Edge e : adjacentList)
      e.status = Edge.STATUS.UNVISITED;
    prevs = new ArrayList<Node>();
  }



  @Override
  public int compareTo(Node other) {
    double diff = dis - other.dis;
    if (diff < 0)
      return -1;
    else if (diff > 0)
      return 1;
    else
      return 0;
  }

}
