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

  public N getOppositeNode(N end) {
    assert (end.equals(from) || end.equals(to));
    if (end.equals(from)) return to;
    return from;
  }

  public VisitStatus getVisitStatus() {
    return visitStatus;
  }

  public void setVisitStatus(VisitStatus visitStatus) {
    this.visitStatus = visitStatus;
  }
}
