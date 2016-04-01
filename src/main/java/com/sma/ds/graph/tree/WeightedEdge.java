package com.sma.ds.graph.tree;

import com.sma.ds.graph.basic.Edge;
import com.sma.ds.graph.basic.Node;

/** Undirected weighted edge. */
public class WeightedEdge<N extends Node> extends Edge<N> {

  private int weight;

  public WeightedEdge(N from, N to, int weight) {
    super(from, to);
    this.weight = weight;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return getStartNode() + "->" + getEndNode() + ":" + getWeight();
  }
}
