package com.shuoma.alg.bit;

import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;

public class CountOneInIntegerTest extends TestCase {

  public void testCount() throws Exception {
    for (int i = 0; i < 10; i++) {
      int[] res = new int[2];
      int x = RandomUtil.r.nextInt(Integer.MAX_VALUE);
      res[1] = CountOneInInteger.count(x);
      res[0] = countDummy(x);
      if (res[0] != res[1]) {
        System.out.println(x + "  " + Integer.toBinaryString(x) + " " + res[0] + " " + res[1]);
      }
    }
  }

  int countDummy(int x) {
    char[] bin = Integer.toBinaryString(x).toCharArray();
    //System.out.println(bin.length);
    int cnt = 0;
    for (char c : bin) {
      if (c == '1') {
        cnt++;
      }
    }
    return cnt;
  }
}
