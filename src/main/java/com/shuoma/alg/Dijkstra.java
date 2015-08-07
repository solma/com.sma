package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueueT;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.graph.basic.Edge;
import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.basic.PathNode;
import com.shuoma.ds.graph.basic.VisitStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Tag(algs = BreadthFirstSearch, dss = PriorityQueueT)
public class Dijkstra {
  public List<Node> path = new ArrayList<>();
  public static final boolean verbose = true;

  /**
   * return all shortest paths; null if not find
   *
   * @param g
   * @param end: target node; if null, then traverse
   */
  public List<List<Node>> find(Graph g, PathNode start, PathNode end) {
    if (verbose) {
      System.out.println("**** Dijkstra Searching Illustration ****");
    }

    if (start == null || end == null) return new ArrayList<>();

    PriorityQueue<PathNode> pq = new PriorityQueue<>();
    start.setVisitStatus(VisitStatus.VISITED);
    pq.add(start);

    int lvl = 0;
    while (pq.size() > 0) {
      PathNode cur = pq.poll();
      if (cur.equals(end)) break;

      cur.setVisitStatus(VisitStatus.EXPANDED);
      if (verbose) {
        System.out.println("level " + lvl + " :  pos:" + cur + " , value:" + cur.getValue() + ",  dis:"
            + cur.getDistance());
      }
      for (Edge<PathNode> e : cur.getAdjacentEdges()) {
        if (e.getVisitStatus() == VisitStatus.UNVISITED) {
          e.setVisitStatus(VisitStatus.VISITED);
          PathNode oppo = e.getOppositeNode(cur);

          double newDist = oppo.getValue() + cur.getDistance();
          if (newDist <= oppo.getDistance()) {
            if (newDist < oppo.getDistance()) {
              pq.remove(oppo);
              oppo.setDistance(newDist);
              pq.add(oppo);
              oppo.clearPrevNodes();
            }
            oppo.addPrevNode(cur);
          }

        }
      }
      lvl++;
    }

    return g.buildAllPaths(end);
  }

  public void traverse(Graph g, PathNode start) {
    find(g, start, null);
  }
}
