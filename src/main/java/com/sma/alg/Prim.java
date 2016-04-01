package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.MinimumSpanningTree;

import com.sma.annotation.Tag;
import com.sma.ds.graph.basic.Edge;
import com.sma.ds.graph.basic.Graph;
import com.sma.ds.graph.basic.Node;
import com.sma.ds.graph.basic.PathNode;
import com.sma.ds.graph.basic.VisitStatus;
import com.sma.ds.graph.tree.Tree;
import com.sma.ds.graph.tree.TreeNode;
import com.sma.ds.graph.tree.WeightedEdge;

import java.util.LinkedList;
import java.util.PriorityQueue;

@Tag(algs = DynamicProgramming, dss = MinimumSpanningTree)
public class Prim extends MinimumSpanningTreeFactory {
  public static final boolean verbose = true;

  @Override
  public <N extends Node> Tree build(Graph<N, WeightedEdge<N>> g) {
    if (verbose) {
      System.out.println("**** Kruskal Building Illustration ****");
    }

    PathNode start = (PathNode) new LinkedList<>(g.getAllNodes()).get(0);
    if (start == null) return null;
    PriorityQueue<PathNode> pq = new PriorityQueue<>();
    start.setVisitStatus(VisitStatus.VISITED);
    pq.add(start);

    Tree tree = null;

    int lvl = 0;
    while (pq.size() > 0) {
      PathNode cur = pq.poll();
      cur.setVisitStatus(VisitStatus.EXPANDED);

      if (lvl == 0)
        tree = new Tree(new TreeNode(cur));
      else {
        TreeNode father = tree.treeNodes.get(cur.getPrevNodes().get(0).getId());
        TreeNode child = new TreeNode(cur);
        father.addChild(child);
        tree.treeNodes.put(child.getId(), child);
        if (verbose) {
          System.out.println("edge " + lvl + " :  father:" + father.getValue() + ",  child:"
              + child.getValue());
        }
      }

      for (Edge<PathNode> e : cur.getAdjacentEdges()) {
        if (e.getVisitStatus() == VisitStatus.UNVISITED) {
          PathNode oppo = e.getOppositeNode(cur);
          if (oppo.getVisitStatus() == VisitStatus.UNVISITED) {
            oppo.setVisitStatus(VisitStatus.VISITED);
            oppo.addPrevNode(cur);

            double newDist = oppo.getValue();
            if (oppo.getDistance() > newDist) {
              pq.remove(oppo);
              oppo.setDistance(newDist);
              pq.add(oppo);
            }

          }
        }
      }
      lvl++;
    }
    return tree;
  }
}
