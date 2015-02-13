package com.shuoma.util;

import com.shuoma.ds.graph.basic.Graph;
import com.shuoma.ds.graph.basic.Node;
import com.shuoma.ds.graph.tree.WeightedEdge;
import com.shuoma.ds.misc.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

  public static int[] genRandomArrayWithMinSize(int minSize) {
    return genRandomArrayWithMinSize(minSize, MAX_RANDOM_VALUE);
  }

  public static int[] genRandomArrayWithMinSize(int minSize ,int maxValue) {
    int length = r.nextInt(10) + minSize; // at least two elements
    return genRandomArray(length, maxValue, false, true);
  }

  public static List<Interval> genRandomIntervalList(int n, int maxValue) {
    int size = 5 + r.nextInt(n);
    List<Interval> intervals = new ArrayList<>(size);

    for (int i =0; i < size; i++) {
      int[] interval = genRandomKNumbers(2, maxValue);
      intervals.add(
          new Interval(Math.min(interval[0], interval[1]), Math.max(interval[0], interval[1])));
    }
    return intervals;
  }

  /** Generate random K different numbers from 0~N. */
  public static int[] genRandomKNumbers(int k, int n) {
    int[] array = shuffle(ArrayUtil.getNaturalArray(n));
    return Arrays.copyOf(array, k);
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
      int[] twoDifferentRandomNumbers = genRandomKNumbers(2, nNodes);
      Node from = nodes[twoDifferentRandomNumbers[0]];
      Node to = nodes[twoDifferentRandomNumbers[1]];
      edges.add(new WeightedEdge<>(from, to, r.nextInt(maxWeight) + 1));
    }
    return new Graph(edges);
  }



  public static int[] leftShift(int[] a, int start) {
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

  public static int[] shuffle(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      int swap = i + r.nextInt(arr.length - i);
      ArrayUtil.swap(arr, i, swap);
    }
    return arr;
  }
}
