package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Tag(algs = DynamicProgramming, dss = Array)
public class Knapsack {

  public int itemMajorOrder(int capacity, int[] weight, int[] value, boolean noLimitForEachItem) {
    int n = weight.length;
    int[] maxValue = new int[capacity + 1];
    Map<Integer, Integer> prev = new HashMap<>();
    int capacityStart, capacityEnd, step;
    for (int i = 1; i <= n; i++) {
      if (noLimitForEachItem) { //dp[i][j] = f(dp[i][j-weight[k])], dp[i-1][j])
        capacityStart = 0;
        capacityEnd = capacity + 1;
        step = 1;
      } else { //dp[i][j] = f(dp[i-1][j-weight[k])], dp[i-1][j])
        capacityStart = capacity;
        capacityEnd = -1;
        step = -1;
      }
      for (int j = capacityStart; j != capacityEnd; j+= step) {
        if (j >= weight[i - 1] && maxValue[j] < maxValue[j - weight[i - 1]] + value[i - 1]) {
          maxValue[j] = maxValue[j - weight[i - 1]] + value[i - 1];
          prev.put(maxValue[j], i - 1);
        }
        if (i == n) {
          break;
        }
      }
    }

    List<Integer> packedItems = new LinkedList<>();
    for (int i = maxValue[capacity]; i > 0; i -= value[prev.get(i)]) {
      packedItems.add(0, prev.get(i));
    }
    //System.out.println("packed items: " + packedItems);
    return maxValue[capacity];
  }

  public int capacityMajorOrder(int capacity, int[] weight, int[] value) {
    int n = weight.length;
    int[][] maxValue = new int[n + 1][capacity + 1];
    for (int j = 0; j <= capacity; j++) {
      for (int i = 1; i <= n; i++) {
        maxValue[i][j] = maxValue[i - 1][j];
        if (j >= weight[i - 1]) {
          int tmpValue = maxValue[i - 1][j - weight[i - 1]] + value[i - 1];
          if (tmpValue > maxValue[i][j]) {
            maxValue[i][j] = tmpValue;
          }
        }
      }
    }
    return maxValue[n][capacity];
  }
}
