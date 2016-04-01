package com.sma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestZigzagSubsequenceTest {

  @Test
  public void testLongestZigZagSubsequence() throws Exception {
    LongestZigzagSubsequence ins = new LongestZigzagSubsequence();

    int[] array = new int[] {1, 7, 4, 9, 2, 5};
    assertEquals(6, ins.longestZigZagSubsequence(array));

    array = new int[] {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
    assertEquals(7, ins.longestZigZagSubsequence(array));

    array = new int[] {44};
    assertEquals(1, ins.longestZigZagSubsequence(array));

    array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertEquals(2, ins.longestZigZagSubsequence(array));

    array = new int[] {70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32};
    assertEquals(8, ins.longestZigZagSubsequence(array));

    array = new int[] {374, 40, 854, 203, 203, 156, 362, 279, 812, 955, // 7 ok  812 ends    i = 9
        600, 947, 978, 46, 100, 953, 670, 862, 568, 188, // 13 ok  568 ends    (i=19)
        67, 669, 810, 704, 52, 861, 49, 640, 370, 908, // 21 ok 908 ends    (i=29)
        477, 245, 413, 109};
    //477, 245, 413, 109, 659, 401, 483, 308, 609, 120}; // 30 not ok
    assertEquals(24, ins.longestZigZagSubsequence(array));

    array = new int[] {374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 600, 947, 978, 46, 100, 953,
        670, 862, 568, 188, 67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 477, 245, 413, 109, 659,
        401, 483, 308, 609, 120, 249, 22, 176, 279, 23, 22, 617, 462, 459, 244};
    assertEquals(36, ins.longestZigZagSubsequence(array));
  }
}
