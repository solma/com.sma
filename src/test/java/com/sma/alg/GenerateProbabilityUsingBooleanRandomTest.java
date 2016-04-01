package com.sma.alg;

import org.junit.Test;

import java.util.Arrays;

public class GenerateProbabilityUsingBooleanRandomTest {

  @Test
  public void testProb91() throws Exception {
    GenerateProbabilityUsingBooleanRandom ins = new GenerateProbabilityUsingBooleanRandom();
    int[] cnt = new int[2];
    for (int times = 0; times < 1000000; times ++) {
      cnt[ins.Prob91()]++;
    }
    System.out.println(Arrays.toString(cnt));
  }
}
