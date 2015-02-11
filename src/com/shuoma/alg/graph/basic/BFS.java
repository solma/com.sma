package com.shuoma.alg.graph.basic;

import com.shuoma.ds.graph.basic.Edge;
import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
  public ArrayList<Node> path = new ArrayList<>();
  public static final boolean verbose = true;

  /**
   * return all paths; and null if not find
   *
   * @param g
   * @param end: target node; if null, then traverse
   */
  public ArrayList<ArrayList<Node>> find(Graph g, Node start, Node end) {
    if (verbose) {
      System.out.println("**** BFS Searching Illustration ****");
    }
    if (start == null || end == null) return new ArrayList<>();

    LinkedList<Node> curLvl = new LinkedList<>();
    start.visitStatus = Node.STATUS.VISITED;
    start.dis = 0;
    curLvl.add(start);

    int lvl = 0;
    while (curLvl.size() > 0) {
      if (verbose) {
        System.out.println("level " + lvl + " : " + curLvl);
      }
      LinkedList<Node> nextLvl = new LinkedList<>();
      while (curLvl.size() > 0) {
        Node cur = curLvl.poll();
        if (cur.equals(end)) break;

        cur.visitStatus = Node.STATUS.EXPANED;
        for (Edge e : cur.getAdjacentEdges()) {
          if (e.status == Edge.STATUS.UNVISITED) {
            Node oppo = e.getOppositeNode(cur);

            // if(oppo.visitStatus==Node.STATUS.UNVISITED){
            // e.visitStatus=STATUS.VISITED;
            // oppo.visitStatus=Node.STATUS.VISITED;
            // nextLvl.add(oppo);
            // }else
            if (oppo.dis < Integer.MAX_VALUE) {
              e.status = Edge.STATUS.CROSSED;
              if (verbose) {
                System.out.print("Cycle Detected : ");
                // g.printPath(g.buildAllPath(oppo, cur),true);
                System.out.println(" --> " + oppo.value);
              }
            }
            double newDist = lvl + 1;
            if (newDist <= oppo.dis) {
              if (newDist < oppo.dis) {
                oppo.dis = newDist;
                oppo.prevs.clear();
                nextLvl.add(oppo);
              }
              oppo.prevs.add(cur);
            }
          }
        }
      }

      curLvl = nextLvl;
      lvl++;
    }

    ArrayList<Node> path = new ArrayList<>();
    path.add(end);
    return g.buildAllPaths(start, end, path);
  }

  public void traverse(Graph g, Node start) {
    find(g, start, null);
  }
}
