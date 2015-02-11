package com.shuoma.alg.graph.basic.shortestpath;

import com.shuoma.ds.graph.basic.Edge;
import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
  public ArrayList<Node> path = new ArrayList<>();
  public static final boolean verbose = true;

  /**
   * return all shortest paths; null if not find
   *
   * @param g
   * @param end: target node; if null, then traverse
   */
  public ArrayList<ArrayList<Node>> find(Graph g, Node start, Node end) {
    if (verbose) {
      System.out.println("**** Dijkstra Searching Illustration ****");
    }

    if (start == null || end == null) return new ArrayList<>();

    PriorityQueue<Node> pq = new PriorityQueue<>();
    start.dis = 0;
    start.visitStatus = Node.STATUS.VISITED;
    pq.add(start);

    int lvl = 0;
    while (pq.size() > 0) {
      Node cur = pq.poll();
      if (cur.equals(end)) break;

      cur.visitStatus = Node.STATUS.EXPANED;
      if (verbose) {
        System.out.println("level " + lvl + " :  pos:" + cur + " , value:" + cur.value + ",  dis:"
            + cur.dis);
      }
      for (Edge e : cur.getAdjacentEdges()) {
        if (e.status == Edge.STATUS.UNVISITED) {
          e.status = Edge.STATUS.VISITED;
          Node oppo = e.getOppositeNode(cur);

          double newDist = oppo.value + cur.dis;
          if (newDist <= oppo.dis) {
            if (newDist < oppo.dis) {
              pq.remove(oppo);
              oppo.dis = newDist;
              pq.add(oppo);
              oppo.prevs.clear();// remove old prevs
            }
            oppo.prevs.add(cur);
          }

        }
      }
      lvl++;
    }

    ArrayList<Node> path = new ArrayList<Node>();
    path.add(end);
    return g.buildAllPaths(start, end, path);
  }

  public void traverse(Graph g, Node start) {
    find(g, start, null);
  }
}
