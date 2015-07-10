package com.shuoma.alg;

import static com.shuoma.util.RandomUtil.r;

import static com.shuoma.util.ArrayUtil.intToDoubleArray;
import com.shuoma.util.RandomUtil;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.junit.Test;

import java.util.Arrays;

public class KnapsackTest {

  @Test
  public void test() throws Exception {
    Knapsack ins = new Knapsack();
    for (int i = 0; i < 1; i++) {
      int len = r.nextInt(10) + 3;
      int maxNumber = 10;
      int[] weights = RandomUtil.genRandomArray(len, maxNumber, true, false);
      int[] values = RandomUtil.genRandomArray(len, maxNumber, true, false);
      double multiplier = (5 + r.nextInt(5)) / 10.0;
      int W = (int) ((new Sum()).evaluate(intToDoubleArray(weights), 0, len) * multiplier);

      weights = new int[]{2, 3, 4};
      values = new int[]{2, 3, 3};
      W = 8;

      int[] res = new int[3];
      res[0] = packDummy(W, weights, values);
      res[1] = ins.itemMajorOrder(W, weights, values, true);
      res[2] = ins.capacityMajorOrder(W, weights, values);
      if (res[0] != res[1] || res[0] != res[2]) {
        System.out.println("capacity: " + W);
        System.out.println("weights: " + Arrays.toString(weights));
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("max value: " + Arrays.toString(res));
        System.out.println();
      }
    }
  }

  int packDummy(int W, int[] weights, int[] values) {
    int[] maxV = new int[1];
    packDummyRecur(weights, values, W, 0, 0, 0, maxV);
    return maxV[0];
  }

  void packDummyRecur(int[] weights, int[] values, int w, int i, int totW, int totV, int[] maxV) {
    if (totW > w) return;
    if (totV > maxV[0]) maxV[0] = totV;
    if (i == weights.length) return;
    packDummyRecur(weights, values, w, i + 1, totW + weights[i], totV + values[i], maxV);
    packDummyRecur(weights, values, w, i + 1, totW, totV, maxV);
  }
}
