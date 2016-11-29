package com.sma.alg;

import com.sma.util.ArrayUtil;
import com.sma.util.RandomUtil;
import junit.framework.TestCase;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class CountOfSmallerNumbersAfterSelfTest extends TestCase {

  public void testCountInversionPerIndex() throws Exception {
    CountOfSmallerNumbersAfterSelf ins = new CountOfSmallerNumbersAfterSelf();
    for (int i = 0; i < 1000; i++) {
      int[] arr = RandomUtil.shuffle(ArrayUtil.getNaturalArray(RandomUtil.r.nextInt(30) + 15));
      int[][] res = new int[2][arr.length];
      res[0] = ins.countInversion(arr);
      res[1] = dummyCountInversion(arr);
      assertArrayEquals(res[0], res[1]);
    }
  }

  public void testCountInversion() throws Exception {
    CounterInversionDivideConquer ins = new CounterInversionDivideConquer();
    for (int i = 0; i < 1000; i++) {
      int[] arr = RandomUtil.shuffle(ArrayUtil.getNaturalArray(RandomUtil.r.nextInt(30) + 15));
      int[] res = new int[2];
      res[0] = ins.count(arr);
      res[1] = sum(dummyCountInversion(arr));
      if (res[0] != res[1]) {
        System.out.println(Arrays.toString(arr));
      }
      assertEquals(res[0], res[1]);
    }
  }

  int[] dummyCountInversion(int[] arr) {
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[i]) res[i]++;
      }
    }
    return res;
  }

  int sum(int[] arr) {
    return sum(arr, 0, arr.length - 1);
  }

  int sum(int[] arr, int s, int e) {
    if (s > e) { return 0; }
    return arr[s] + sum(arr, s + 1, e);
  }
}
