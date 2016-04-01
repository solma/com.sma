package com.sma.alg;

import static org.junit.Assert.assertTrue;

import com.sma.ds.misc.Interval;
import com.sma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WeightedIntervalSchedulingTest {

  @Test
  public void testComputeLastCompatibleInterval() throws Exception {
    for (int i = 0; i < 100; i++) {
      List<Interval> intervals = RandomUtil.genRandomListOfWeightedIntervals(10, 5, 10, false);
      Collections.sort(intervals, new Comparator<Interval>() {
        @Override public int compare(Interval o1, Interval o2) {
          return o1.end == o2.end ? (o1.start - o2.start) : (o1.end - o2.end);
        }
      });
      WeightedIntervalScheduling ins = new WeightedIntervalScheduling(intervals);
      assertTrue(Arrays.equals(ins.computeLastCompatibleInterval(),
          ins.computeLastCompatibleInterval1()));
    }
  }
}
