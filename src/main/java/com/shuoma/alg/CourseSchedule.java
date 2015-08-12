package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BreadthFirstSearch;
import static com.shuoma.annotation.Tag.Algorithm.DepthFirstSearch;
import static com.shuoma.annotation.Tag.Algorithm.TopologicalSorting;
import static com.shuoma.annotation.Tag.DataStructure.MatrixGraph;
import static com.shuoma.annotation.Tag.DataStructure.QueueT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Tag(algs = {BreadthFirstSearch, DepthFirstSearch, TopologicalSorting}, dss = {MatrixGraph, QueueT}, references = LeetCode)
/**
 There are a total of n courses you have to take, labeled from 0 to n - 1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, is it possible for you to
 finish all courses?

 For example:
 2, [[1,0]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 So it is possible.

 2, [[1,0],[0,1]]
 There are a total of 2 courses to take. To take course 1 you should have finished course 0,
 and to take course 0 you should also have finished course 1. So it is impossible.
 */
// no cycle <=> has a topological sorting
public class CourseSchedule {

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    return topologicalSortingBfs(numCourses, prerequisites);
  }

  int[] topologicalSortingBfs(int numCourses, int[][] prerequisites) {
    int[] indegree = new int[numCourses];
    List<List<Integer>> edges = buildEdges(numCourses, prerequisites, indegree);

    int count = numCourses;
    Queue<Integer> zeroInDegreeQueue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        zeroInDegreeQueue.offer(i);
      }
    }

    int[] ret = new int[numCourses];
    int idx = 0;

    while (!zeroInDegreeQueue.isEmpty()) {
      int current = zeroInDegreeQueue.poll();
      ret[idx++] = current;
      for (int i : edges.get(current)) {
        if (--indegree[i] == 0) {
          zeroInDegreeQueue.offer(i);
        }
      }
      count--;
    }

    return count == 0 ? ret : new int[0];
  }

  List<List<Integer>> buildEdges(int n, int[][] prerequisites, int[] inDegrees) {
    List<List<Integer>> edges = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      edges.add(new LinkedList<Integer>());
    }

    for (int[] edge : prerequisites) {
      int src = edge[0], des = edge[1];
      edges.get(src).add(des);
      inDegrees[des]++;
    }
    return edges;
  }

  int[] topologicalSortingDfs(int numCourses, int[][] prerequisites) {
    List<List<Integer>> edges = buildEdges(numCourses, prerequisites, new int[numCourses]);
    BitSet visited = new BitSet(numCourses);
    BitSet onStack = new BitSet(numCourses);
    List<Integer> order = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if(!visited.get(i) && dfsCycle(i, visited, onStack, edges, order)) {
        return new int[0];
      }
    }
    return ArrayUtil.integerListToIntArray(order);
  }

  boolean dfsCycle(int start, BitSet visited, BitSet onStack, List<List<Integer>> edges, List<Integer> order) {
    visited.set(start);
    onStack.set(start);
    for (int to: edges.get(start)) {
      if (!visited.get(to)) {
        if (dfsCycle(to, visited, onStack, edges, order))
          return true;
      } else if (onStack.get(to)) {
        return true;
      }
    }
    onStack.clear();
    order.add(0, start);
    return false;
  }
}
