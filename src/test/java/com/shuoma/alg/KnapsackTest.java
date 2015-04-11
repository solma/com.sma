package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KnapsackTest {

  @Test public void testPack() throws Exception {
    Knapsack ins = new Knapsack();
    int[] weights = new int[] {2, 2, 6, 5, 4};
    int[] values = new int[] {6, 3, 5, 4, 6};
    int W = 12;
    assertEquals(17, ins.pack(W, weights, values));
  }
}
