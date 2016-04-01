package com.sma.ds.graph.basic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** Generic graph. */
public class Graph<N extends Node, E extends Edge<N>> {

  private final List<E> edges;
  private final Map<N, Map<N, E>> edgeMap;
  private final List<N> nodes;
  private final Map<String, N> nodeMap;

  /** Default constructor, so subclasses can use this. */
  public Graph() {
    this.edges = new LinkedList<>();
    this.edgeMap = new HashMap<>();
    this.nodeMap = new HashMap<>();
    this.nodes = new LinkedList<>();
  }

  /** Build a graph given a list of edges. */
  public Graph(List<E> edges) {
    this();
    initializeNodeAndEdgeMap(edges);
  }

  /** Return all edges of the graph. */
  public List<E> getAllEdges() {
    return edges;
  }

  /** Return all nodes of the graph. */
  public List<N> getAllNodes() {
    return nodes;
  }

  /** Return the edge given starting and ending node pair; return null if no such edge exists. */
  public E getEdge(N from, N to) {
    Map<N, E> outgoingEdges = edgeMap.get(from);
    if (outgoingEdges == null) {
      return null;
    }
    return outgoingEdges.get(to);
  }

  /** Return node by Id. */
  public N getNode(String id) {
    return nodeMap.get(id);
  }

  public List<List<PathNode>> buildAllPaths(PathNode cur) {
    List<List<PathNode>> allPaths = new LinkedList<>();
    if (cur.getPrevNodes().isEmpty()) {
      LinkedList<PathNode> path = new LinkedList<>();
      path.add(cur);
      allPaths.add(path);
    } else {
      for (PathNode prev : cur.getPrevNodes()) {
        for (List<PathNode> path : buildAllPaths(prev)) {
          path.add(cur);
          allPaths.add(path);
        }
      }
    }
    return allPaths;
  }

  public void printPath(List<List<PathNode>> paths) {
    printPath(paths, false);
  }

  public void printPath(List<List<PathNode>> paths, boolean byValue) {
    System.out.println("All Paths:");
    for (List<PathNode> path : paths) {
      for (int i = 0; i < path.size(); i++) {
        if (byValue)
          System.out.print(path.get(i).getValue());
        else
          System.out.print(path.get(i));
        if (i < path.size() - 1) System.out.print(" --> ");
      }

      System.out.println();
    }
  }

  public void resetVisitStatus() {
    for (Node node : nodes) {
      node.resetVisitStatus();
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (N node : edgeMap.keySet()) {
      sb.append(node + "->" + edgeMap.get(node).values() + "\n");
    }
    return sb.toString();
  }

  private void initializeNodeAndEdgeMap(List<E> inputEdges) {
    for (E edge : inputEdges) {
      edges.add(edge);

      N from = edge.getStartNode(), to = edge.getEndNode();
      nodeMap.put(from.getId(), from);
      nodeMap.put(to.getId(), to);

      Map<N, E> outgoingEdges = edgeMap.get(from);
      if (outgoingEdges == null) {
        outgoingEdges = new HashMap<>();
        edgeMap.put(from, outgoingEdges);
      }
      outgoingEdges.put(to, edge);
    }

    for (N node : nodeMap.values()) {
      nodes.add(node);
    }
  }
}
