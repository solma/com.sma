package com.shuoma.alg;

import static com.shuoma.util.RandomUtil.genRandomKNumbers;
import static com.shuoma.util.RandomUtil.genRandomListOfIntervals;

import com.shuoma.ds.misc.Interval;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OverlappedIntervalsTest extends TestCase {

  @Test
  public void testGetOverlapped() throws Exception {
    OverlappedIntervals ins = new OverlappedIntervals();

    int n = 10, maxValue = 20;
    for (int i = 0; i < 20; i++) {
      List<Interval> intervals = genRandomListOfIntervals(n, maxValue, true);

      int[] query = genRandomKNumbers(2, intervals.get(0).start, intervals.get(intervals.size() - 1).end);
      Arrays.sort(query);
      Interval qI = new Interval(query[0], query[1]);

      //List<Interval> intervals = buildIntervalList(new int[][] {{0, 1}, {0, 5}, {15, 32}, {21, 33}, {22, 48}, {29, 32}, {35, 46}, {44, 49}});
      List[] res = new List[2];
      res[0] = dummyGetOverlapped(intervals, qI);
      res[1] = ins.getOverlapped(intervals, qI);
      if (!res[0].equals(res[1])) {
        System.out.println(intervals + " " + qI);
        System.out.println(res[0]);
        System.out.println(res[1]);
      }
    }
  }

  List<Interval> dummyGetOverlapped(List<Interval> li, Interval query) {
    List<Interval> ret = new LinkedList<>();
    for (Interval interval : li) {
      if (interval.overlap(query)) {
        ret.add(interval);
      }
    }
    return ret;
  }
}
