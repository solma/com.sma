package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(dss = Array, source = LeetCode)
public class GasStation {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int min = 0, total = 0, len = gas.length, index = -1;
    for (int i = 0; i < len; i++) {
      total += gas[i] - cost[i];
      if (total < min) {
        index = i;
        min = total;
      }
    }
    return total >= 0 ? index + 1 : -1;
  }

  // TLE
  public int canCompleteCircuitTLE(int[] gas, int[] cost) {
    int n = gas.length;

    // find the maximum difference between cost[i] and gas[i]
    int max = 0;
    List<Integer> candidates = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      if (gas[i] - cost[i] >= max) {
        if (gas[i] - cost[i] > max) {
          max = gas[i] - cost[i];
          candidates.clear();
        }
        candidates.add(i);
      }
    }
    // System.out.println(candidates);

    int gasCnt = 0;
    for (Integer start : candidates) {
      int j = start;
      while (true) {
        gasCnt += gas[j];
        if (gasCnt >= cost[j]) {
          gasCnt -= cost[j];
          j = (j + 1) % n;
        } else {
          break;
        }
        if (j == start)
          return j;
      }
    }
    return -1;
  }
}
