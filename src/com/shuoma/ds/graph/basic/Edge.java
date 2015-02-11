package com.shuoma.ds.graph.basic;

/** Generic edge. */
public class Edge<N extends Node> {
  private N from;
  private N to;
  private VisitStatus visitStatus;

  public Edge(N from, N to) {
    this.from = from;
    this.to = to;
  }

  public N getEndNode() {
    return to;
  }

  public N getStartNode() {
    return from;
  }

  public Node getOppositeNode(Node one) {
    assert (one.equals(from) || one.equals(to));
    if (one.equals(from)) return to;
    return from;
  }

  public VisitStatus getVisitStatus() {
    return visitStatus;
  }

  public void setVisitStatus(VisitStatus visitStatus) {
    this.visitStatus = visitStatus;
  }
}
