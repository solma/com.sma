package com.shuoma.ds.graph;

import com.shuoma.alg.graph.basic.BFS;
import com.shuoma.alg.graph.basic.DFS;
import com.shuoma.alg.graph.basic.shortestpath.Dijkstra;
import com.shuoma.alg.graph.tree.mst.Kruskal;
import com.shuoma.ds.graph.basic.Edge;
import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.basic.PathNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Matrix as a graph where each element in the matrix is regarded a node
 *
 * @author Shuo
 *
 */
public class MatrixGraph extends Graph {
  // {-1, 0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}
  public static final int[][] NEIGHBOR_ELEMENTS = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

  class MatrixNode extends PathNode {
    public static final String ID_DELIMITER = "-";

    public MatrixNode(int row, int col, double v) {
      super(row + ID_DELIMITER + col, v, Integer.MAX_VALUE);
    }


  }

  class MatrixEdge extends Edge {
    public MatrixEdge(Node f, Node t) {
      super(f, t);
      resetVisitStatus();
    }

    public boolean equals(Edge other) {
      Node from = getStartNode(), to = getEndNode();
      Node otherFrom = other.getStartNode(), otherTo = other.getEndNode();
      if ((from.equals(otherFrom) && to.equals(otherTo))
          || (from.equals(otherTo) && to.equals(otherFrom))) {
        return true;
      }
      return false;
    }

    @Override
    public String toString() {
      return "(" + getStartNode() + "," + getEndNode() + ")";
    }
  }

  public Node getNode(int row, int col) {
    return getNode(row + MatrixNode.ID_DELIMITER + col);
  }

  /**
   * Convert a 2d array to a graph
   *
   * @param board
   */
  public MatrixGraph(double[][] board) {
    // check input 2d array
    if (board == null) throw new IllegalArgumentException();
    int nrow = board.length;
    if (nrow == 0) throw new IllegalArgumentException();
    int ncol = board[0].length;

    // add nodeMap;
    Map<String, Node> nodeMap = new HashMap<String, Node>();
    for (int i = 0; i < nrow; i++) {
      for (int j = 0; j < ncol; j++) {
        MatrixNode node = new MatrixNode(i, j, board[i][j]);
        nodeMap.put(node.getId(), node);
      }
    }

    // add edges;
    for (Node node : nodeMap.values()) {
      String[] rowAndCol = node.getId().split(MatrixNode.ID_DELIMITER);
      int row = Integer.parseInt(rowAndCol[0]);
      int col = Integer.parseInt(rowAndCol[1]);
      for (int i = 0; i < NEIGHBOR_ELEMENTS.length; i++) {
        String adjacentNodeID =
            (row + NEIGHBOR_ELEMENTS[i][0]) + MatrixNode.ID_DELIMITER
                + (col + NEIGHBOR_ELEMENTS[i][1]);
        if (nodeMap.containsKey(adjacentNodeID)) {
          node.getAdjacentEdges().add(new MatrixEdge(node, nodeMap.get(adjacentNodeID)));
        }
      }
    }
  }

  public static void main(String[] args) {
    double[][] matrix = {
        {7, 2, 3, 1},
        {2, 5, 1, 1},
        {3, 1, 5, 3},
        {3, 5, 3, 1}};
    MatrixGraph graph = new MatrixGraph(matrix);

    /*
     * for(Edge edge: graph.getEdgeList()){ System.out.println(edge); }
     */
    Node start = graph.getNode(0, 0), end = graph.getNode(3, 3);

    // bfs
    BFS bfs = new BFS();
    graph.resetVisitStatus();
    graph.printPath(bfs.find(graph, start, end));

    ArrayList<Node> path = new ArrayList<Node>();
    // graph.printPath( graph.buildAllPaths(start, end, path) );

    // dfs
    DFS dfs = new DFS();
    graph.resetVisitStatus();
    // dfs.find(graph, start, end );
    // graph.printPath( graph.buildPath(start, end) );

    // iddfs
    /*
     * for(int depth=1;depth<graph.nodeMap.size();depth++){ graph.resetVisitStatus(); if(dfs.find(graph, start,
     * end,depth)!=null){ graph.printPath( graph.buildPath(start, end) ); break; } }
     */

    // dijkstra
    Dijkstra dijkstra = new Dijkstra();
    graph.resetVisitStatus();
    graph.printPath(dijkstra.find(graph, start, end));


    // Kruskal
    Kruskal kruskal = new Kruskal();
    graph.resetVisitStatus();
    // Tree tree=kruskal.buildMST(graph, start);
    // graph.printPath(tree.traverse(TRAVERSAL_ORDER.PREORDER), true);
  }
}
