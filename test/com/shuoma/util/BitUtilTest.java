package com.shuoma.util;

import static com.shuoma.util.BitUtil.clearBit;
import static com.shuoma.util.BitUtil.clearBits;
import static com.shuoma.util.BitUtil.flipBit;
import static com.shuoma.util.BitUtil.getBit;
import static com.shuoma.util.BitUtil.isPowerOfFour;
import static com.shuoma.util.BitUtil.isPowerOfTwo;
import static com.shuoma.util.BitUtil.maxWithoutComparisonOperator;
import static com.shuoma.util.BitUtil.nextNumberWithSameNumberOfOnes;
import static com.shuoma.util.BitUtil.prevNumberWithSameNumberOfOnes;
import static com.shuoma.util.BitUtil.reverseBits;
import static com.shuoma.util.BitUtil.setBit;
import static com.shuoma.util.BitUtil.setBits;
import static com.shuoma.util.BitUtil.swapOddAnEvenBits;
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
  public void testClearBits() throws Exception {
    assertEquals(0x5550, clearBits(0x5555, 3));
    assertEquals(0x5540, clearBits(0x5555, 4));
  }

  @Test
  public void testFlipBit() throws Exception {
    assertEquals(0xF0, flipBit(0xF1L, 0));
    assertEquals(0xF3, flipBit(0xF1L, 1));
  }

  @Test
  public void testGetBit() throws Exception {
    assertEquals(1, getBit(0xF1L, 0));
    assertEquals(0, getBit(0xF1L, 1));
  }

  @Test
  public void testIsPowerOfFour() throws Exception {
    assertEquals(false, isPowerOfFour(32));
    assertEquals(true, isPowerOfFour(16));
    assertEquals(true, isPowerOfFour(1));
  }

  @Test
  public void testIsPowerOfTwo() throws Exception {
    assertEquals(true, isPowerOfTwo(32));
    assertEquals(true, isPowerOfTwo(1));
    assertEquals(false, isPowerOfTwo(3));
  }

  @Test
  public void testMaxWithoutComparisonOperator() throws Exception {
    assertEquals(0xFFFF, maxWithoutComparisonOperator(0xFFFF, 0xFFFE));
  }

  @Test
  public void testNextNumberWithSameNumberOfOnes() throws Exception {
    assertEquals(0x31, nextNumberWithSameNumberOfOnes(0x2C));
    assertEquals(0xF000000000000000L, nextNumberWithSameNumberOfOnes(0xF000000000000000L));
  }

  @Test
  public void testPrevNumberWithSameNumberOfOnes() throws Exception {
    assertEquals(0x2C, prevNumberWithSameNumberOfOnes(0x31));
    assertEquals(0x2A, prevNumberWithSameNumberOfOnes(0x2C));
    assertEquals(1L, prevNumberWithSameNumberOfOnes(1L));
  }

  @Test
  public void testReverse() throws Exception {
    assertEquals(0x5100000000000000L, reverseBits(0x8AL));
    assertEquals(0x8a00000000000000L, reverseBits(0x51L));
  }

  @Test
  public void testSetBit() throws Exception {
    assertEquals(0xF3, setBit(0xF1L, 1));
    assertEquals(0xF7, setBit(0xF3L, 2));
  }

  @Test
  public void testSetBits() throws Exception {
    assertEquals(0xF7, setBits(0xF1L, 2, 0, 7));
  }

  @Test
  public void testSwapTwoBits() throws Exception {
    assertEquals(0xF2, swapTwoBits(0xF1L, 0, 1));
    assertEquals(0xF1, swapTwoBits(0xF2L, 0, 1));
  }

  @Test
  public void testSwapOddAnEvenBits() throws Exception {
    assertEquals(0xAAAA, swapOddAnEvenBits(0x5555));
    assertEquals(0xCCCC, swapOddAnEvenBits(0xCCCC));
  }
}
