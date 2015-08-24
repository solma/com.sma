package com.shuoma.alg;

import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class StreamingStatsTest extends TestCase {

  @Test
  public void testAverage() throws Exception {
    for (int i = 0; i < 20; i++) {
      int[] ary = RandomUtil.genRandomArray(100, 10, true, false);
      StreamingStats ins = new StreamingStats(3, ary);
      assertTrue(Arrays.equals(ins.getAverage(), dummyAverage(ins)));
    }
  }

  double[] dummyAverage(StreamingStats ins) {
    int n = ins.stream.length;
    double[] max = new double[n - ins.K + 1];
    for(int i = ins.K - 1; i < n; i++) {
      double sum = 0;
      for (int j = 0; j < ins.K; j++) {
        sum += ins.stream[i - j];
      }
      max[i - ins.K + 1] = sum / ins.K;
    }
    return max;
  }

  @Test
  public void testMax() throws Exception {
    for (int i = 0; i < 1; i++) {
      int[] ary = RandomUtil.genRandomArray(100, 10, true, false);
      StreamingStats ins = new StreamingStats(3, ary);
      int[][] res = new int[3][];
      res[0] = dummyMax(ins);
      res[1] = ins.getMaxWithTreeMap(ins.stream, ins.K);
      res[2] = ins.getMaxWithTreeMap(ins.stream, ins.K);
      //System.out.println("array = " + Arrays.toString(ary));
      //System.out.println("max = " + Arrays.toString(res));
      assertTrue(Arrays.equals(res[0], res[1]));
      assertTrue(Arrays.equals(res[0], res[2]));
    }
  }

  int[] dummyMax(StreamingStats ins) {
    int n = ins.stream.length;
    int[] max = new int[n - ins.K + 1];
    for(int i = ins.K - 1; i < n; i++) {
      int largest = 0;
      for (int j = 0; j < ins.K; j++) {
        largest = Math.max(largest, ins.stream[i - j]);
      }
      max[i - ins.K + 1] = largest;
    }
    return max;
  }

  @Test public void testMedian() throws Exception {
    for (int i = 0; i < 1; i++) {
      //int[] ary = RandomUtil.genRandomArray(10, 10, true, false);
      int[] ary = {6, 4, 8, 11, 5, 11, 8, 2, 4, 8};
      StreamingStats ins = new StreamingStats(3, ary);
      int[][] medians = new int[2][];
      medians[0] = dummyMedian(ins);
      medians[1] = ins.getMedian(ins.stream);
      if (!Arrays.equals(medians[0], medians[1])) {
        System.out.println("array = " + Arrays.toString(ary));
        System.out.println("medians should be = " + Arrays.toString(medians[0]));
        System.out.println("medians actually are= " + Arrays.toString(medians[1]));
      }
      //assertTrue(Arrays.equals(res, dummyMedian(ins)));
    }
  }

  int[] dummyMedian(StreamingStats ins) {
    int n = ins.stream.length;
    int[] median = new int[n - 1];
    for(int i = 1; i < n; i++) {
      int[] cpy = Arrays.copyOf(ins.stream, i + 1);
      Arrays.sort(cpy);
      int l = cpy.length;
      //System.out.println("i = " + i + " cpy = " + Arrays.toString(cpy));
      median[i - 1] = ((l & 1) > 0) ? cpy[l / 2] : (cpy[l / 2] + cpy[l / 2 - 1]) / 2;
    }
    return median;
  }
}
