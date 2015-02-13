package com.shuoma.alg.graph.tree.mst;

import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.tree.Tree;
import com.shuoma.ds.graph.tree.WeightedEdge;
import com.shuoma.util.RandomUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Kruskal extends MinimumSpanningTreeFactory {

  public static void main(String[] args) {
    Graph randomWeightedGraph = RandomUtil.genRandomUndirectedWeightedGraph(7, 10, 10);
    System.out.println(randomWeightedGraph.toString());
    getInstance().build(randomWeightedGraph);
  }

  private static Kruskal factory = new Kruskal();

  public static Kruskal getInstance() {
    return factory;
  }

  @Override
  public <N extends Node> Tree build(Graph<N, WeightedEdge<N>> graph) {
    List<WeightedEdge<N>> edges = new LinkedList<>(graph.getAllEdges());
    Collections.sort(edges, new Comparator<WeightedEdge<N>>() {
      @Override public int compare(WeightedEdge<N> e1, WeightedEdge<N> e2) {
        return e1.getWeight() - e2.getWeight();
      }
    });

    Set<N> addedNodes = new HashSet<>();
    int n = graph.getAllNodes().size();

    List<WeightedEdge> treeEdges = new LinkedList<>();
    for (WeightedEdge<N> edge : edges) {
      if (treeEdges.size() == n - 1) break;
      N from = edge.getStartNode(), to = edge.getEndNode();
      if (!(addedNodes.contains(from) && addedNodes.contains(to))) {
        treeEdges.add(edge);
        addedNodes.add(from);
        addedNodes.add(to);
      }
    }

    for(WeightedEdge edge : treeEdges) {
      System.out.println(edge);
    }

    return null;
  }
}
