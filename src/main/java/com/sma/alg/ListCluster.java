package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Greedy;
import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
// Given a LinkedList of Objects in order, the list of object is processed by some program and give out a Object[].
// Find how many clusters in the result Object[]. A cluster means all the Object in the cluster is consecutive in
// the original list. Object could be anything, can't directly compare.
//
// For example: the original list is: ABCDEFGHIJK, the result is:   D E F J G H C
// cluster 1: C D E F G H ;   cluster2: J
 */

@Tag(algs = Greedy, dl = D3, dss = Hash,  references = Interview)
public class ListCluster<T> {
  public static void main(String[] args) {
    ListCluster ins = new ListCluster();

    Character[] output = new Character[]{'F', 'D', 'E', 'J', 'G', 'H', 'C'};
    Character[] ordering = new Character[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};

    Collection<Set<Character>> clusters = ins.findClusters(ordering, output);
    for(Set<Character> cluster : clusters){
      System.out.println(cluster);
    }
  }

  Collection<Set<T>> findClusters(T[] ordering, T[] output) {

    List<Map<T, T>> neighbors = new ArrayList<>(2);
    neighbors.add(new HashMap<T, T>());
    neighbors.add(new HashMap<T, T>());

    T prev = null;
    for(int i = 0; i < ordering.length; i++) {
      T cur = ordering[i];
      if (prev != null) {
        neighbors.get(0).put(cur, prev); //0 : predecessor
      }
      if (i < ordering.length - 1) {
        neighbors.get(1).put(cur, ordering[i + 1]); //1 : successor
      }
      prev = cur;
    }
    System.out.println(neighbors);

    Set<T> visited = new HashSet<>();
    Set<T> needsToVisit = new HashSet<>(Arrays.asList(output));

    Collection<Set<T>> clusters = new LinkedList<>();

    for(T ele : output) {
      if (visited.contains(ele)) continue;

      Set<T> cluster = new HashSet<>();
      clusters.add(cluster);

      for (Map<T, T> neighbor : neighbors) {
        T cur = ele;
        while(true) {
          cluster.add(cur);
          visited.add(cur);
          if (!neighbor.containsKey(cur)
              || !needsToVisit.contains(neighbor.get(cur))
              || visited.contains(neighbor.get(cur))) {
            break;
          }
          cur = neighbor.get(cur);
        }
      }
    }
    return clusters;
  }
}
