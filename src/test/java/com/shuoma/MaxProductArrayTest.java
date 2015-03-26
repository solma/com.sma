package com.shuoma;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class MaxProductArrayTest extends TestCase {

  @Test
  public void testMaxProduct() throws Exception {
    MaxProductArray ins = new MaxProductArray();
    for (int i = 0; i < 1; i++) {
      //int[] A = RandomUtil.genRandomArray(10, 10, false, true);
      int[] A = {3, -2, 5};
      int[][] res = new int[2][3];
      res[0] = ins.maxProductSimple(A);
      res[1] = ins.maxProductDP(A);
      assertEquals(res[0][2], res[1][2]);
      if (res[0][2] != res[1][2]){
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
      }
    }
  }
}
