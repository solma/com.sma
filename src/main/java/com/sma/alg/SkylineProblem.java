/*
 A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings,
 return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings
where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate
in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of
some horizontal segment in the skyline except the last point in the list,
which always has a y-coordinate 0 and is used to mark the skyline's termination
where the rightmost building ends. Any ground between the leftmost and rightmost buildings
should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline.
For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable;
the three lines of height 5 should be merged into one in the final output as such:
[...,[2 3],[4 5],[12 7],...]

Example 1:
Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
 */
package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.*;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(dl = D3, dss = {Array, MonotonicSequence}, references = LeetCode)
public class SkylineProblem {

  public static void main(String[] args) {
    //int[][] buildings = new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
    int[][] buildings = new int[][] {{2, 9, 3}, {2, 9, 7}};
    for (int[] p : new SkylineProblem().getSkyline(buildings)) {
      System.out.println(Arrays.toString(p));
    }
  }

  List<int[]> getSkyline(int[][] buildings) {
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
        if (a.x != b.x) { return a.x - b.x; }
        // closing edge before starting edge
        if (a.isStart ^ b.isStart) return a.isStart ? -1 : 1;
        // both start
        if (a.isStart) { return b.height - a.height; }
        // both end
        return a.height - b.height;
      }
    });

    // process edges
    PriorityQueue<Integer> heightHeap = new PriorityQueue<>(10, Collections.reverseOrder());

    /***
     *   -----
     *   |   |
     * ---   ---
     * |       |
     */
    for (Edge edge : edges) {
      if (edge.isStart) {
        if (heightHeap.isEmpty() || edge.height > heightHeap.peek()) {
          result.add(new int[] {edge.x, edge.height});
        }
        heightHeap.add(edge.height);
      } else {
        heightHeap.remove(edge.height);
        if (!heightHeap.isEmpty() && edge.height > heightHeap.peek()) {
          result.add(new int[] {edge.x, heightHeap.peek()});
          continue;
        }
        if (heightHeap.isEmpty()) {
          result.add(new int[] {edge.x, 0});
        }
      }
    }

    return result;
  }

  // the buildings are already sort by the left value of x axis
  List<int[]> getSkyline1(int[][] buildings) {
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
