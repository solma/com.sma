package com.shuoma.alg.graph.tree.mst;

import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.tree.Tree;
import com.shuoma.ds.graph.tree.WeightedEdge;

public abstract class MinimumSpanningTreeFactory {
  public abstract <N extends Node> Tree build(Graph<N, WeightedEdge<N>> graph);
}
