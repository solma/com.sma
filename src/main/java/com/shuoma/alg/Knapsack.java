package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Array;

import com.shuoma.annotation.Tag;

import java.util.Deque;
import java.util.LinkedList;

@Tag(algs = DynamicProgramming, dss = Array)
public class Knapsack {

  public int pack(int space, int[] weight, int[] utility) {
    int n = weight.length;
    int[] maxUtility = new int[space + 1];
    int[] prev = new int[space + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = space; j >= 0; j--) {
        if (j >= weight[i - 1] && maxUtility[j] < maxUtility[j - weight[i - 1]] + utility[i - 1]) {
          maxUtility[j] = maxUtility[j - weight[i - 1]] + utility[i - 1];
          prev[j] = j - weight[i - 1];
        }
        if (i == n) {
          break;
        }
      }
    }

    Deque<Integer> packed = new LinkedList<>();
    for (int i = space; i > 0; i = prev[i]) {
      packed.addFirst(i - prev[i]);
    }
    System.out.println(packed);
    return maxUtility[space];
  }
}
