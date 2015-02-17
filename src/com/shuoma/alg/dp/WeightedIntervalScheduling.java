package com.shuoma.alg.dp;

import com.shuoma.ds.misc.Interval;
import com.shuoma.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeightedIntervalScheduling {

  public static void main(String[] args) {
    List<Interval> list = RandomUtil.genRandomListOfWeightedIntervals(10, 10, 10);
    schedule(list);
  }

  public static List<Interval> schedule(List<Interval> input) {
    List<Interval> ret = new ArrayList<>();
    int n = input.size();
    if (n < 1) return ret;
    if (n < 2) return input;
    Collections.sort(input);
    System.out.println("input: \n" + input);

    int[] lastCompatiblePredecessor = new int[n];
    for (int i = 0; i < n; i++) {
      lastCompatiblePredecessor[i] = input.get(i).bisect(input);
    }
    System.out.println("last compatible array: " + Arrays.toString(lastCompatiblePredecessor));

    /**
     * DP part
     */
    double[] dpRes = new double[n];
    boolean[] dpDecision = new boolean[n];
    for (int i = 0; i < n; i++) {
      Interval cur = input.get(i);
      int lastCompatible = lastCompatiblePredecessor[i];

      if (i > 0) dpRes[i] = dpRes[i - 1];// not using cur
      // using cur
      double sum = cur.weight;
      if (lastCompatible != -1) sum += dpRes[lastCompatible];
      if (sum > dpRes[i]) {
        dpRes[i] = sum;
        dpDecision[i] = true;
      }
      System.out.println("i=" + i + " cur interval: " + cur + " last compatibl=" + lastCompatible
          + " new sum=" + dpRes[i]);
    }
    System.out.println("dp decision array: " + Arrays.toString(dpDecision));

    for (int i = n - 1; i >= 0;) {
      if (dpDecision[i]) {
        ret.add(0, input.get(i));
        i = lastCompatiblePredecessor[i];
      } else {
        i--;
      }
    }
    System.out.println("ret: \n" + ret);
    return ret;
  }
}
