package com.shuoma.alg.streaming;

import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class SlidingWindowStatsTest extends TestCase {

  @Test
  public void testAverage() throws Exception {
    for (int i = 0; i < 20; i++) {
      int[] ary = RandomUtil.genRandomArray(100, 10, true, false);
      SlidingWindowStats ins = new SlidingWindowStats(3, ary);
      assertTrue(Arrays.equals(ins.average(), averageDummy(ins)));
    }
  }

  double[] averageDummy(SlidingWindowStats ins) {
    double[] max = new double[ins.stream.length - ins.K + 1];
    for(int i = ins.K - 1; i < ins.stream.length; i++) {
      double sum = 0;
      for (int j = 0; j < ins.K; j++) {
        sum += ins.stream[i - j];
      }
      max[i - ins.K + 1] = (int)(sum / ins.K * 100) / 100.0;
    }
    return max;
  }

  @Test
  public void testMax() throws Exception {
    for (int i = 0; i < 20; i++) {
      int[] ary = RandomUtil.genRandomArray(100, 10, true, false);
      SlidingWindowStats ins = new SlidingWindowStats(3, ary);
      assertTrue(Arrays.equals(ins.max(), maxDummy(ins)));
    }
  }

  int[] maxDummy(SlidingWindowStats ins) {
    int[] max = new int[ins.stream.length - ins.K + 1];
    for(int i = ins.K - 1; i < ins.stream.length; i++) {
      int largest = 0;
      for (int j = 0; j < ins.K; j++) {
        largest = Math.max(largest, ins.stream[i - j]);
      }
      max[i - ins.K + 1] = largest;
    }
    return max;
  }
}
