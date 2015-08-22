package com.shuoma.util;

import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.tree.WeightedEdge;
import com.shuoma.ds.misc.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtil {

  public static final int MAX_RANDOM_VALUE = 1000;
  public static final Random r = new Random();


  /**
   *
   * @param length: size of array
   * @param maxNumber: possible maximum value for elements
   * @param oneBased: index starting from 1
   * @param canBeNegative: elements can be negative
   * @return random array
   */
  public static int[] genRandomArray(int length, int maxNumber, boolean oneBased, boolean canBeNegative) {
    int[] ret = new int[length];
    for (int i = 0; i < length; i++) {
      ret[i] = r.nextInt(maxNumber + 1);
    }
    if (oneBased) {
      for (int i = 0; i < length; i++) {
        ret[i] += 1;
      }
    }
    if (canBeNegative) {
      for (int i = 0; i < length; i++) {
        ret[i] *= (r.nextBoolean() ? 1 : -1);
      }
    }
    return ret;
  }

  public static byte[] genRandomBinaryByteArray(int length) {
    byte[] ret = new byte[length];
    for (int i = 0; i < length; i++) {
      ret[i] = (byte) (r.nextInt(2) > 0 ? 1 : -1);
    }
    return ret;
  }

  public static int[] genRandomArrayWithMinSize(int minSize) {
    return genRandomArrayWithMinSize(minSize, MAX_RANDOM_VALUE);
  }

  public static int[] genRandomArrayWithMinSize(int minSize ,int maxValue) {
    int length = r.nextInt(7) + minSize; // at least minSize elements
    return genRandomArray(length, maxValue, false, true);
  }

  public static List<Interval> genRandomListOfWeightedIntervals(int n, int maxRange, int maxWeight, boolean isDisjoint) {
    int size = 5 + r.nextInt(n);
    List<Interval> intervals = new ArrayList<>(size);

    int offset = 0;
    for (int i = 0; i < size; i++) {
      int[] interval = genRandomKNumbers(2, offset, offset + maxRange);
      int start = Math.min(interval[0], interval[1]);
      int end = Math.max(interval[0], interval[1]);
      intervals.add(new Interval(start, end, r.nextInt(maxWeight)));
      if (isDisjoint) {
        offset = end;
      }
    }
    return intervals;
  }

  public static List<Interval> genRandomListOfIntervals(int n, int maxRange, boolean isDisjoint) {
    return genRandomListOfWeightedIntervals(n, maxRange, 1, isDisjoint);
  }

  /** Generate random K different numbers within range. */
  public static int[] genRandomKNumbers(int k, int min, int max) {
    int[] res = new int[k];
    Set<Integer> exists = new HashSet<>();
    for (int i = 0; i < k ; i++) {
      do {
        res[i] = r.nextInt(max - min) + min;
      } while (exists.contains(res[i]));
      exists.add(res[i]);
    }
    return res;
  }

  public static int[][] genRandomMatrix(int nRow, int nCol, int maxNumber, boolean oneBased, boolean canBeNegative) {
    assert(nRow > 0 && nCol > 0);
    int[][] board = new int[nRow][nCol];
    for (int i = 0; i < nRow; i++) {
      board[i] = genRandomArray(nCol, maxNumber, oneBased, canBeNegative);
    }
    return board;
  }

  public static int[] generateRandomRotateArray() {
    int[] array = RandomUtil.genRandomArrayWithMinSize(2);
    Arrays.sort(array);
    int n = array.length;
    int rotatedIdx = RandomUtil.r.nextInt(n);
    // System.out.println("rotated idx="+(n-rotatedIdx)%n );
    return RandomUtil.leftShift(array, rotatedIdx);
  }

  public static <N extends Node> Graph<N, WeightedEdge<N>> genRandomUndirectedWeightedGraph(int nNodes, int nEdges, int maxWeight) {
    Node[] nodes = new Node[nNodes];
    for (int i = 0; i < nNodes; i++) {
      nodes[i] = new Node(String.valueOf(i));
    }

    List<WeightedEdge<Node>> edges = new LinkedList<>();
    for (int i = 0; i < nEdges; i++) {
      int[] twoDifferentRandomNumbers = genRandomKNumbers(2, 0, nNodes);
      Node from = nodes[twoDifferentRandomNumbers[0]];
      Node to = nodes[twoDifferentRandomNumbers[1]];
      edges.add(new WeightedEdge<>(from, to, r.nextInt(maxWeight) + 1));
    }
    return new Graph(edges);
  }



  public static int[] leftShift(final int[] a, int start) {
    int n = a.length;
    int[] ret = new int[n];
    for (int i = start; i < n; i++) {
      ret[i - start] = a[i];
    }
    for (int i = 0; i < start; i++) {
      ret[i + n - start] = a[i];
    }
    return ret;
  }

  public static int[] shuffle(final int[] arr) {
    return shuffle(arr, 0, arr.length - 1);
  }

  public static int[] shuffle(final int[] arr, int start, int end) {
    int n = end - start + 1;
    int[] ret = new int[n];
    System.arraycopy(arr, start, ret, 0, n);
    for(int i = start; i <= end; i++) {
      int swap = i + r.nextInt(n - (i - start));
      ArrayUtil.swap(ret, i - start, swap - start);
    }
    return arr;
  }
}
