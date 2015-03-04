package com.shuoma.util;

import static com.shuoma.util.BitUtil.clearBit;
import static com.shuoma.util.BitUtil.flipBit;
import static com.shuoma.util.BitUtil.getBit;
import static com.shuoma.util.BitUtil.isPowerOfFour;
import static com.shuoma.util.BitUtil.isPowerOfTwo;
import static com.shuoma.util.BitUtil.reverse;
import static com.shuoma.util.BitUtil.set;
import static com.shuoma.util.BitUtil.setBit;
import static com.shuoma.util.BitUtil.swapTwoBits;

import junit.framework.TestCase;
import org.junit.Test;

public class BitUtilTest extends TestCase {

  @Test
  public void testClearBit() throws Exception {
    assertEquals(0xF0, clearBit(0xF1L, 0));
    assertEquals(0xF1, clearBit(0xF3L, 1));
  }

  @Test
  public void testFlipBit() throws Exception {
    assertEquals(0xF0, flipBit(0xF1L, 0));
    assertEquals(0xF3, flipBit(0xF1L, 1));
  }

  @Test
  public void testIsPowerOfFour() throws Exception {
    assertEquals(false, isPowerOfFour(32));
    assertEquals(true, isPowerOfFour(16));
    assertEquals(true, isPowerOfFour(1));
  }

  @Test
  public void testGetBit() throws Exception {
    assertEquals(1, getBit(0xF1L, 0));
    assertEquals(0, getBit(0xF1L, 1));
  }

  @Test
  public void testIsPowerOfTwo() throws Exception {
    assertEquals(true, isPowerOfTwo(32));
    assertEquals(true, isPowerOfTwo(1));
    assertEquals(false, isPowerOfTwo(3));
  }

  @Test
  public void testReverse() throws Exception {
    assertEquals(0x5100000000000000L, reverse(0x8AL));
    assertEquals(0x8a00000000000000L, reverse(0x51L));
  }

  @Test
  public void testSet() throws Exception {
    assertEquals(0xF7, set(0xF1L, 2, 0, 7));
  }

  @Test
  public void testSetBit() throws Exception {
    assertEquals(0xF3, setBit(0xF1L, 1));
    assertEquals(0xF7, setBit(0xF3L, 2));
  }

  @Test
  public void testSwapTwoBits() throws Exception {
    assertEquals(0xF2, swapTwoBits(0xF1L, 0, 1));
    assertEquals(0xF1, swapTwoBits(0xF2L, 0, 1));
  }
}
