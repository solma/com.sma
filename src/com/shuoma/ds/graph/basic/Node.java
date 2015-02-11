package com.shuoma.ds.graph.basic;

import java.util.ArrayList;
import java.util.List;

/** Generic node. */
public class Node {
  private List<Edge> adjacentEdges;
  private List<Node> adjacentNodes;
  private final String id;
  private double value;
  private VisitStatus visitStatus;

  public Node(String id) {
    this.id = id;
    adjacentEdges = new ArrayList<>();
    resetVisitStatus();
  }

  public Node(String id, double value) {
    this(id);
    this.value = value;
  }

  public Node(Node node) {
    this(node.getId(), node.getValue());
  }

  public List<Edge> getAdjacentEdges() {
    return adjacentEdges;
  }

  public List<Node> getAdjacentNodes() {
    return adjacentNodes;
  }

  public String getId() {
    return id;
  }

  public double getValue() {
    return value;
  }

  public VisitStatus getVisitStatus() {
    return visitStatus;
  }

  public void resetVisitStatus() {
    visitStatus = VisitStatus.UNVISITED;
    for (Edge e : adjacentEdges)
      e.setVisitStatus(VisitStatus.UNVISITED);
  }

  public void setVisitStatus(VisitStatus visitStatus) {
    this.visitStatus = visitStatus;
  }

  @Override
  public String toString() {
    return id;
  }
}
