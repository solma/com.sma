package com.shuoma.alg;

import static org.junit.Assert.assertTrue;

import com.shuoma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;

public class ParitySortingTest {

  @Test
  public void testParitySorting() throws Exception {
    ParitySorting ins = new ParitySorting();
    for (int i = 0; i < 100; i++) {
      int[] arr = RandomUtil.genRandomArray(20, 20, true, false);
      int[] cpy = Arrays.copyOf(arr, arr.length);
      dummyParitySorting(arr);
      ins.paritySorting(cpy);
//      System.out.println(Arrays.toString(arr));
//      System.out.println(Arrays.toString(cpy));
//      System.out.println();
      assertTrue(Arrays.equals(arr, cpy));
    }
  }

  void dummyParitySorting(int[] arr) {
    int n = arr.length;
    if (n < 2) {
      return;
    }
    int[] cpy = new int[n];
    int j = 0;
    for (int i = 0; i < n; i++) {
      if ((arr[i] & 1) > 0) {
        cpy[j++] = arr[i];
      }
    }
    for (int i = 0; i < n; i++) {
      if ((arr[i] & 1) == 0) {
        cpy[j++] = arr[i];
      }
    }
    System.arraycopy(cpy, 0, arr, 0, n);
  }
}
