package com.shuoma.alg.number;

import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class SubarraySumTest extends TestCase {

  @Test
  public void testFindSum() throws Exception {
    for (int i = 0; i < 100; i++) {
      int K = RandomUtil.r.nextInt(10) + 10;
      int[] a;
      a = RandomUtil.genRandomArray(10, K / 2, true, false);
      //a = new int[]{7, 10, 3, 7, 10, 3, 7, 4, 6, 8};
      int[][] res = new int[2][];
      res[0] = findSumDummy(a, K);
      res[1] = (new SubarraySum(a, K)).findSum();
      if (res[0][2] != res[1][2]) {
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(res[0]) + " " + Arrays.toString(res[1]));
      }
    }
  }

  int[] findSumDummy(int[] a, int K) {
    int n = a.length;
    int[] sum = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      sum[i] = sum[i - 1] + a[i - 1];
    }

    int diff = Integer.MAX_VALUE;
    int[] res = new int[3];
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        int diffN = Math.abs(sum[i] - sum[j] - K);
        if (diffN < diff) {
          diff = diffN;
          res[0] = j;
          res[1] = i - 1;
        }
      }
    }
    res[2] = diff;
    return res;
  }
}
