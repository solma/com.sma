package com.sma.alg;

import com.sma.util.RandomUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Map;

public class AllFactorsOfNumberTest extends TestCase {

  @Test
  public void testFactorization() throws Exception {
    AllFactorsOfNumber ins = new AllFactorsOfNumber();
    for (int i = 0; i < 10; i++) {
      int n = RandomUtil.r.nextInt(Integer.MAX_VALUE / 10) + 2;
      assertEquals(n, factorsToNumber(ins.factorization(n)));
    }
  }

  int factorsToNumber(Map<Integer, Integer> factors) {
    int n = 1;
    for (int factor : factors.keySet()) {
      n *= Math.pow(factor, factors.get(factor));
    }
    return n;
  }
}
