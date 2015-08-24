package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.DataStructure.UndirectedGraph;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Tag(algs = BreadthFirstSearch, dl = D3, dss = {Hash, UndirectedGraph}, references = LeetCode)
public class CloneGraph {

  public static void main(String[] args) {
    new CloneGraph().main();
  }

  public void main() {
    UndirectedGraphNode node = new UndirectedGraphNode(-1);
    UndirectedGraphNode node2 = new UndirectedGraphNode(1);
    node.neighbors.add(node2);
    cloneGraph(node);
  }

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) { return node; }

    Queue<UndirectedGraphNode> queue = new LinkedList<>();
    queue.offer(node);
    Map<UndirectedGraphNode, UndirectedGraphNode> cloned = new HashMap<>();
    cloned.put(node, new UndirectedGraphNode(node.label));

    while (!queue.isEmpty()) {
      UndirectedGraphNode curNode = queue.poll();
      for (UndirectedGraphNode neighbor : curNode.neighbors) {
        if (!cloned.containsKey(neighbor)) {
          cloned.put(neighbor, new UndirectedGraphNode(neighbor.label));
          queue.offer(neighbor);
        }
        cloned.get(curNode).neighbors.add(cloned.get(neighbor));
      }
    }
    return cloned.get(node);
  }
}
