package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HIndexTest {

  @Test public void testHIndex() throws Exception {
    HIndex ins = new HIndex();
    assertEquals(3, ins.hIndex(new int[] {5, 2, 4, 6}));
    assertEquals(2, ins.hIndex(new int[] {3, 2, 5}));
    assertEquals(3, ins.hIndex(new int[] {4, 2, 3, 5}));
    assertEquals(4, ins.hIndex(new int[] {8, 6, 7, 5}));
    assertEquals(4, ins.hIndex(new int[] {9, 7, 6, 8}));
    assertEquals(4, ins.hIndex(new int[] {10, 7, 6, 8}));
    assertEquals(3, ins.hIndex(new int[] {3, 13, 2, 3, 33}));
    assertEquals(3, ins.hIndex(new int[] {1, 1, 3, 6, 64, 15, 3}));
  }
}
