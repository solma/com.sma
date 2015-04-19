package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.MinimumSpanningTree;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.tree.Tree;
import com.shuoma.ds.graph.tree.WeightedEdge;

@Tag(dss = MinimumSpanningTree)
public abstract class MinimumSpanningTreeFactory {
  public abstract <N extends Node> Tree build(Graph<N, WeightedEdge<N>> graph);
}
