package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.MinimumSpanningTree;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.graph.basic.Edge;
import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.basic.PathNode;
import com.shuoma.ds.graph.basic.VisitStatus;
import com.shuoma.ds.graph.tree.Tree;
import com.shuoma.ds.graph.tree.TreeNode;
import com.shuoma.ds.graph.tree.WeightedEdge;

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
