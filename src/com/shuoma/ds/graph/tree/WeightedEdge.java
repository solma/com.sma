package com.shuoma.ds.graph.tree;

import com.shuoma.ds.graph.basic.Edge;
import com.shuoma.ds.graph.basic.Node;

/** Edge with a weight. */
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
