package com.sma.alg;

import com.sma.util.ArrayUtil;
import com.sma.util.RandomUtil;
import junit.framework.TestCase;

import static org.junit.Assert.assertArrayEquals;

public class CountOfSmallerNumbersAfterSelfTest extends TestCase {

  public void testCountInversion() throws Exception {
    CountOfSmallerNumbersAfterSelf ins = new CountOfSmallerNumbersAfterSelf();
    for (int i = 0; i < 1000; i++) {
//      int [] arr = {3, 4, 0, 2, 1};
      int[] arr = RandomUtil.shuffle(ArrayUtil.getNaturalArray(RandomUtil.r.nextInt(30) + 15));
//      System.out.println(Arrays.toString(arr));
      int[][] res = new int[2][arr.length];
      res[0] = ins.countInversion(arr);
      res[1] = dummyCountInversion(arr);
      assertArrayEquals(res[0], res[1]);
//      System.out.println(Arrays.toString(arr));
//      System.out.println(Arrays.toString(res[0]));
//      System.out.println(Arrays.toString(res[1]));
//      System.out.println();
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
}
