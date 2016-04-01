package com.sma.alg;

import static org.junit.Assert.assertEquals;

import com.sma.util.RandomUtil;
import org.junit.Test;

public class BitwiseXorOfAllNumbersInRangeTest {

  @Test
  public void testGetXor() throws Exception {
    BitwiseXorOfAllNumbersInRange ins = new BitwiseXorOfAllNumbersInRange();
    for (int i = 0; i < 10; i++) {
      int m = RandomUtil.r.nextInt(Integer.MAX_VALUE);
      int n = m + RandomUtil.r.nextInt(Integer.MAX_VALUE - m);
      assertEquals(dummyRangeBitwiseXor(m, n), ins.getXor(m, n));
    }
  }

  int dummyRangeBitwiseXor(int m, int n) {
    int ret = m;
    for (int i = m + 1; i <= n; i++) {
      ret ^= i;
    }
    return ret;
  }
}
