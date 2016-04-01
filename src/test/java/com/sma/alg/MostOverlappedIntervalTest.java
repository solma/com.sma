package com.sma.alg;

import static com.sma.ds.misc.Interval.buildIntervalList;
import static com.sma.util.RandomUtil.genRandomListOfIntervals;

import com.sma.ds.misc.Interval;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MostOverlappedIntervalTest extends TestCase {

  @Test public void testMostOverlapped() throws Exception {
    MostOverlappedInterval ins = new MostOverlappedInterval();

    int n = 10, maxValue = 50;
    for (int i = 0; i < 1; i++) {
      List<Interval> intervals = genRandomListOfIntervals(n, maxValue, false);
      intervals = buildIntervalList(
          new int[][] {{0, 1}, {0, 5}, {15, 32}, {21, 33}, {22, 48}, {29, 32}, {35, 46}, {44, 49}});
      int[][] res = new int[2][3];
      res[0] = dummyMostOverlapped(intervals);
      res[1] = ins.mostOverlappedInterval(intervals);
      if (res[0][0] != res[1][0]) {
        System.out.println(intervals);
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
      }
    }
  }

  int[] dummyMostOverlapped(List<Interval> intervals) {
    int[] result = new int[3];
    for (int i = 0; i < intervals.size(); i++) {
      int cnt = 0;
      Interval interval = intervals.get(i);
      for (int j = 0; j < intervals.size(); j++) {
        if (i == j)
          continue;
        if (interval.overlap(intervals.get(j))) {
          cnt++;
        }
      }
      if (cnt > result[0]) {
        result[0] = cnt;
        result[1] = interval.start;
        result[2] = interval.end;
      }
    }
    return result;
  }
}
