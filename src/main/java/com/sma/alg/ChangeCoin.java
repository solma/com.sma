package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Backtracking;
import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Complexity.Quadratic;

import com.sma.annotation.Tag;
import com.sma.util.CollectionsUtil;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of denominations and a number X, write code to find out
 * the number of unique ways that amount X can be decomposed.
 */
@Tag(algs = {Backtracking, DynamicProgramming}, timecomplexity = Quadratic)
public class ChangeCoin {

  //dp[i][j] = f(dp[i][j-denom[k])], dp[i-1][j])
  int changeCntDp(final int N, final int[] denominations) {
    int m = denominations.length;
    long[] cnt = new long[N + 1];
    cnt[0] = 1;

    for (int j = 1; j <= m; j++) {
      //for (int i = N; i >= 0; i--) // not work requires dp[i][j] = f(dp[i-1][j-denom[k])], dp[i-1][j])
      for (int i = 0; i <= N; i++) { // allow repetition for same denomination, similar to knapsack
        if (i >= denominations[j - 1]) {
          cnt[i] += cnt[i - denominations[j - 1]];
        }
      }
    }
    return (int) cnt[N];
  }

  Set<Map<Integer, Integer>> changeWaysDp(final int n, final int[] denominations) {
    int m = denominations.length;
    long[] cnt = new long[n + 1];
    cnt[0] = 1;

    Map<Integer, List<MutablePair<Integer, Integer>>> backTracking = new HashMap<>();

    for (int j = 1; j <= m; j++) {
      for (int i = n; i >= 0; i--) {
        int k = 1;
        while(i >= k * denominations[j - 1]) {
          cnt[i] += cnt[i - denominations[j - 1] * k];
          k++;
        }
        if (k > 1) {
          CollectionsUtil.upsert(backTracking, i,
            new MutablePair<>(i - denominations[j - 1] * (k - 1), denominations[j - 1]),
            new LinkedList<MutablePair<Integer, Integer>>());
        }
      }
    }
    // CollectionsUtil.printMap(backTracking);
    return getAllWaysOfChange(backTracking, n);
  }

  private Set<Map<Integer, Integer>> getAllWaysOfChange(
      Map<Integer, List<MutablePair<Integer, Integer>>> backTracking, int cur) {
    Set<Map<Integer, Integer>> ret = new HashSet<>();
    if (cur == 0) {
      ret.add(new HashMap<Integer, Integer>());
      return ret;
    }
    for (MutablePair<Integer, Integer> next : backTracking.get(cur)) {
      int face = next.getRight();
      for (int k = 1; k <= (cur - next.getLeft()) / face; k++) {
        for (Map<Integer, Integer> way : getAllWaysOfChange(backTracking, cur - face * k)) {
          CollectionsUtil.increaseMapCounter(way, face, k);
          ret.add(way);
        }
      }
    }
    return ret;
  }

  Set<Map<Integer, Integer>> bottomUpRecursionWithMemory(final int n, final int[] denominations) {
    Map<Integer, Set<Map<Integer, Integer>>> memory = new HashMap<>();
    bottomUpRecursionWithMemory(n, denominations, memory);
    return memory.get(n);
  }

  private Set<Map<Integer, Integer>> bottomUpRecursionWithMemory(final int n, final int[] denominations, Map<Integer, Set<Map<Integer, Integer>>> memory) {
    if (n < 0) {
      return null;
    }

    Set<Map<Integer, Integer>> ret = new HashSet<>();
    if (memory.containsKey(n)) {
      return memory.get(n);
    }
    if (n == 0) {
      ret.add(new HashMap<Integer, Integer>());
      return ret;
    }

    for (int val : denominations) {
      Set<Map<Integer, Integer>> recursionReturn = bottomUpRecursionWithMemory(n - val,
          denominations, memory);
      if (recursionReturn == null) {
        continue;
      }
      for (Map<Integer, Integer> way : recursionReturn) {
        Map<Integer, Integer> wayCpy = new HashMap<>(way);
        CollectionsUtil.increaseMapCounter(wayCpy, val, 1);
        ret.add(wayCpy);
      }
    }
    memory.put(n, ret);
    return ret;
  }
}
