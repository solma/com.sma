package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Difficulty.D3;

import com.sma.annotation.Tag;
import com.sma.ds.misc.Interval;
import com.sma.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Tag(algs = DynamicProgramming, dl = D3, dss = Array)
public class WeightedIntervalScheduling {

  final List<Interval> intervals;

  public WeightedIntervalScheduling(List<Interval> intervals) {
    this.intervals = intervals;
  }

  public static void main(String[] args) {
    List<Interval> list = RandomUtil.genRandomListOfWeightedIntervals(10, 5, 10, false);
    new WeightedIntervalScheduling(list).schedule();
  }

  public List<Interval> schedule() {
    List<Interval> ret = new ArrayList<>();
    int n = intervals.size();
    if (n < 1) return ret;
    if (n < 2) return intervals;
    Collections.sort(intervals, new Comparator<Interval>() {
      @Override public int compare(Interval o1, Interval o2) {
        return o1.end == o2.end ? (o1.start - o2.start) : (o1.end - o2.end);
      }
    });
    //System.out.println("intervals: \n" + intervals);

    int[] lastCompatiblePredecessor = computeLastCompatibleInterval();
    System.out.println(Arrays.equals(lastCompatiblePredecessor, computeLastCompatibleInterval1()));
    //System.out.println("last compatible array: " + Arrays.toString(lastCompatiblePredecessor));

    /**
     * DP part
     */
    double[] dpRes = new double[n];
    boolean[] dpDecision = new boolean[n];
    for (int i = 0; i < n; i++) {
      Interval cur = intervals.get(i);
      int lastCompatible = lastCompatiblePredecessor[i];

      if (i > 0) dpRes[i] = dpRes[i - 1];// not using cur
      // using cur
      double sum = cur.value;
      if (lastCompatible != -1) sum += dpRes[lastCompatible];
      if (sum > dpRes[i]) {
        dpRes[i] = sum;
        dpDecision[i] = true;
      }
      System.out.println("i=" + i + " cur interval: " + cur + " last compatible=" + lastCompatible
          + " new sum=" + dpRes[i]);
    }
    System.out.println("dp decision array: " + Arrays.toString(dpDecision));

    for (int i = n - 1; i >= 0;) {
      if (dpDecision[i]) {
        ret.add(0, intervals.get(i));
        i = lastCompatiblePredecessor[i];
      } else {
        i--;
      }
    }
    System.out.println("ret: \n" + ret);
    return ret;
  }

  int bisect(Interval cur, List<Interval> list) {
    int l = -1, r = list.size();
    while (l + 1 != r) {
      int m = l + (r - l) / 2;
      if (list.get(m).end > cur.start)
        r = m;
      else
        l = m;
    }
    return l;
  }

  int[] computeLastCompatibleInterval() {
    int n = intervals.size();
    int[] lastCompatiblePredecessor = new int[n];
    for (int i = 0; i < n; i++) {
      lastCompatiblePredecessor[i] = bisect(intervals.get(i), intervals);
    }
    return lastCompatiblePredecessor;
  }

  int[] computeLastCompatibleInterval1() {
    int n = intervals.size();
    List<IntervalEndPoint> allPoints = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      Interval interval = intervals.get(i);
      allPoints.add(new IntervalEndPoint(interval.start, 1, i));
      allPoints.add(new IntervalEndPoint(interval.end, 0, i));
    }
    Collections.sort(allPoints, new Comparator<IntervalEndPoint>() {
      @Override public int compare(IntervalEndPoint o1, IntervalEndPoint o2) {
        return o1.val == o2.val ? (o1.isStart - o2.isStart) : (o1.val - o2.val);
      }
    });

    int[] lastCompatiblePredecessor = new int[n];
    int lastClosedInterval = -1;
    for (int i = 0; i < allPoints.size(); i++) {
      IntervalEndPoint p = allPoints.get(i);
      if (p.isStart == 1) {
        lastCompatiblePredecessor[p.intervalIdx] = lastClosedInterval;
      } else {
        lastClosedInterval = p.intervalIdx;
      }
    }
    return lastCompatiblePredecessor;
  }

  public static class IntervalEndPoint {
    int val;
    int isStart;
    int intervalIdx;

    public IntervalEndPoint(int val, int isStart, int intervalIdx) {
      this.val = val;
      this.isStart = isStart;
      this.intervalIdx = intervalIdx;
    }
  }
}
