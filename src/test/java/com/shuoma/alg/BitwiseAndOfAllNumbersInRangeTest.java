package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import com.shuoma.util.RandomUtil;
import org.junit.Test;

public class BitwiseAndOfAllNumbersInRangeTest {

  @Test
  public void testRangeBitwiseAnd() throws Exception {
    BitwiseAndOfAllNumbersInRange ins = new BitwiseAndOfAllNumbersInRange();
    for (int i = 0; i < 10; i++) {
      int m = RandomUtil.r.nextInt(Integer.MAX_VALUE);
      int n = m + RandomUtil.r.nextInt(Integer.MAX_VALUE - m);
      assertEquals(dummyRangeBitwiseAnd(m, n), ins.rangeBitwiseAnd(m, n));
    }
  }

  int dummyRangeBitwiseAnd(int m, int n) {
    if (m == n) {
      return m;
    }
    int ret = -1;
    for (int i = m; i <= n; i++) {
      ret &= i;
      if (ret == 0) {
        break;
      }
    }
    return ret;
  }
}
