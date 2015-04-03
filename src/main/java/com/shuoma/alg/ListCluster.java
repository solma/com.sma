package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.Difficulty.D3;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
// Given a LinkedList of Objects in order, the list of object is processed by some program and give out a Object[].
// Find how many clusters in the result Object[]. A cluster means all the Object in the cluster is consecutive in
// the original list. Object could be anything, can't directly compare.
//
// For example: the original list is: ABCDEFGHIJK, the result is:   D E F J G H C
// cluster 1: C D E F G H ;   cluster2: J
 */

@Tag(algs = Greedy, dl = D3, dss = HashTable)
public class ListCluster<T> {
  public static void main(String[] args) {
    ListCluster ins = new ListCluster();

    Character[] output = new Character[]{'D', 'E', 'F', 'J', 'G', 'H', 'C'};
    Character[] ordering = new Character[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};

    Collection<List<Character>> clusters = ins.findClusters(ordering, output);
    for(List<Character> cluster : clusters){
      System.out.println(cluster);
    }

  }

  Collection<List<T>> findClusters(T[] ordering, T[] output) {

    List<Map<T, T>> neighbors = new ArrayList<>(2);
    neighbors.add(new HashMap<T, T>());
    neighbors.add(new HashMap<T, T>());

    T prev = null;
    for(T ele : ordering) {
      if (prev != null){
        neighbors.get(0).put(ele, prev); //0 : predecessor
        neighbors.get(1).put(prev, ele); //1 : successor
      }
      prev = ele;
    }
    System.out.println(neighbors);

    Map<T, Boolean> visited = new HashMap<>();
    for(T ele : output) {
      visited.put(ele, false);
    }

    Collection<List<T>> clusters = new LinkedList<>();

    for(T ele : output) {
      if (visited.get(ele)) continue;

      visited.put(ele, true);
      List<T> cluster = new LinkedList<>();
      clusters.add(cluster);
      cluster.add(ele);

      for (Map<T, T> neighbor : neighbors) {
        T cur = ele;
        while(neighbor.containsKey(cur)) {
          T next = neighbor.get(cur);
          if (!visited.containsKey(next) || visited.get(next)) break;
          cur = next;
          cluster.add(cur);
          visited.put(cur, true);
        }
      }
    }

    return clusters;
  }
}
