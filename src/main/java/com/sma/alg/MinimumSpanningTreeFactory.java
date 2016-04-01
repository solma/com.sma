package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.MinimumSpanningTree;

import com.sma.annotation.Tag;
import com.sma.ds.graph.basic.Graph;
import com.sma.ds.graph.basic.Node;
import com.sma.ds.graph.tree.Tree;
import com.sma.ds.graph.tree.WeightedEdge;

@Tag(dss = MinimumSpanningTree)
public abstract class MinimumSpanningTreeFactory {
  public abstract <N extends Node> Tree build(Graph<N, WeightedEdge<N>> graph);
}
