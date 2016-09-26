package com.sma.alg;

import com.sma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class RemoveKDigitsTest extends TestCase {

  @Test
  public void testRemove() throws Exception {
    int[] res = new int[3];
    RemoveKDigits ins = new RemoveKDigits();
    for (int i = 0; i < 10000; i++) {
      int n = 123 + RandomUtil.r.nextInt(100000), k = 1 + RandomUtil.r.nextInt(3);
      //int n = 69611, k = 3;
      res[0] = removeDummy(n, k);
      res[1] = ins.remove1(n, k);
      res[2] = ins.remove(n, k);
      assertEquals(res[0], res[1]);
      assertEquals(res[0], res[2]);
      if (res[0] != res[2]) {
        System.out.println(n + ":" + k + "   " + Arrays.toString(res));
      }
    }
  }

  int removeDummy(int n, int k) {
    String s = String.valueOf(n);
    if (k >= s.length()) return 0;
    int min = n;
    for (String com : Combination.combinationsOfSizeNRecursionNOrderTopDown(s, s.length() - k)) {
      min = Math.min(Integer.parseInt(com), min);
    }
    return min;
  }
}
