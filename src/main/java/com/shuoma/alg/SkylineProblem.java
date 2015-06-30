package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

@Tag(dl = D3, dss = {Array}, references = LeetCode) public class SkylineProblem {

  public static void main(String[] args) {
    //int[][] buildings = new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
    int[][] buildings = new int[][] {{2, 9, 3}, {2, 9, 7}};
    for (int[] p : new SkylineProblem().getSkyline(buildings)) {
      System.out.println(Arrays.toString(p));
    }
  }

  // the buildings are already sort by the left value of x axis
  public List<int[]> getSkyline1(int[][] buildings) {
    List<int[]> ret = new LinkedList<>();
    if (buildings == null || buildings.length == 0) {
      return ret;
    }

    int[][] B = buildings;
    int[][] H = new int[B.length * 2][3];

    for (int i = 0; i < B.length; i++) {
      H[2 * i][0] = B[i][0];
      H[2 * i][1] = B[i][2];
      H[2 * i][2] = 1;
      H[2 * i + 1][0] = B[i][1];
      H[2 * i + 1][1] = B[i][2];
      H[2 * i + 1][2] = -1;
    }

    Arrays.sort(H, new Comparator<int[]>() {
      @Override public int compare(int[] o1, int[] o2) {
        return o1[0] - o2[0];
      }
    });

    PriorityQueue<Integer> pq = new PriorityQueue<>(300, Collections.reverseOrder());
    int i = 0;
    int[] pre = null;
    while (i < H.length) {
      int j = i + 1;
      while (j < H.length && H[i][0] == H[j][0]) {
        j++;
      }
      for (int k = i; k < j; k++) {
        if (H[k][2] == -1) {
          pq.remove(H[k][1]);
        }
      }
      for (int k = i; k < j; k++) {
        if (H[k][2] == 1) {
          pq.offer(H[k][1]);
        }
      }
      int h = (pq.peek() == null ? 0 : pq.peek());
      if (pre == null || h != pre[1]) {
        int[] p = {H[i][0], h};
        ret.add(p);
        pre = p;
      }
      i = j;
    }
    return ret;
  }

  public List<int[]> getSkyline(int[][] buildings) {
    List<int[]> result = new LinkedList<>();

    if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
      return result;
    }

    List<Edge> edges = new LinkedList<>();

    // add all left/right edges
    for (int[] building : buildings) {
      Edge startEdge = new Edge(building[0], building[2], true);
      edges.add(startEdge);
      Edge endEdge = new Edge(building[1], building[2], false);
      edges.add(endEdge);
    }

    // sort edges
    Collections.sort(edges, new Comparator<Edge>() {
      public int compare(Edge a, Edge b) {
        if (a.x != b.x)
          return Integer.compare(a.x, b.x);

        if (a.isStart && b.isStart) {
          return Integer.compare(b.height, a.height);
        }

        if (!a.isStart && !b.isStart) {
          return Integer.compare(a.height, b.height);
        }

        return a.isStart ? -1 : 1;
      }
    });

    // process edges
    PriorityQueue<Integer> heightHeap = new PriorityQueue<>(10, Collections.reverseOrder());

    for (Edge edge : edges) {
      if (edge.isStart) {
        if (heightHeap.isEmpty() || edge.height > heightHeap.peek()) {
          result.add(new int[] {edge.x, edge.height});
        }
        heightHeap.add(edge.height);
      } else {
        heightHeap.remove(edge.height);
        if (heightHeap.isEmpty()) {
          result.add(new int[] {edge.x, 0});
        } else if (edge.height > heightHeap.peek()) {
          result.add(new int[] {edge.x, heightHeap.peek()});
        }
      }
    }

    return result;
  }

  class Edge {
    int x;
    int height;
    boolean isStart;

    public Edge(int x, int height, boolean isStart) {
      this.x = x;
      this.height = height;
      this.isStart = isStart;
    }
  }
}
