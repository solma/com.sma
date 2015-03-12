package com.shuoma.alg.number.subarray;

import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class LongestIncreasingSubArrayTest extends TestCase {

  @Test
  public void testLongestIncreasingSubarray() throws Exception {
    LongestIncreasingSubArray ins = new LongestIncreasingSubArray();
    for (int i = 0; i < 10000; i++) {
      int[] a = RandomUtil.genRandomArray(100, 100, true, false);
      int[] res = new int[2];
      res[1] = ins.longestIncreasingSubarray(a);
      res[0] = dummyLongestIncreasingSubarray(a);
      if (res[1] != res[0]) {
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(res));
      }
    }
  }

  int dummyLongestIncreasingSubarray(final int[] a) {
    int n = a.length;
    int max = 1, cur;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        cur = 1;
        for (int k = i + 1; k <= j; k++) {
          if (a[k] > a[k - 1]) {
            cur++;
          } else {
            max = Math.max(max, cur);
            break;
          }
        }
      }
    }
    return max;
  }
}
