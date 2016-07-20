package com.sma.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

import static com.sma.util.BitUtil.*;

public class BitUtilTest extends TestCase {

  @Test
  public void testAdd() throws Exception {
    for (int i = 0; i < 1000; i++) {
      int[] pair = RandomUtil.genRandomKNumbers(2, -220000, 220000);
      long[] res = new long[3];
      res[0] = pair[0] + pair[1];
      res[1] = add(pair[0], pair[1]);
      res[2] = addAlterImpl(pair[0], pair[1]);
      if (!(res[0] == res[1] && res[0] == res[2])) {
        System.out.println(Arrays.toString(pair) + " + " + Arrays.toString(res));
      }
    }
  }

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
  public void testClearLowestSetBit() throws Exception {
    assertEquals(0xAAA8, clearLowestSetBit(0xAAAA));
  }

  @Test
  public void testDivide() throws Exception {
    for (int i = 0; i < 100; i++) {
      int[] pair = RandomUtil.genRandomKNumbers(2, -220000, 220000);
      if (pair[0] / pair[1] != divide(pair[0], pair[1])) {
        System.out.println(Arrays.toString(pair) + " / ");
      }
    }
  }

  @Test
  public void testExtractLowestSetBit() throws Exception {
    assertEquals(0x2, extractLowestSetBit(0xAAAA));
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
  public void testMaxWithoutComparisonOperator() throws Exception {
    assertEquals(0xFFFF, maxWithoutComparisonOperator(0xFFFF, 0xFFFE));
  }

  @Test
  public void testMinus() throws Exception {
    for (int i = 0; i < 100; i++) {
      int[] pair = RandomUtil.genRandomKNumbers(2, -220000, 220000);
      long[] res = new long[2];
      res[0] = pair[0] - pair[1];
      res[1] = minus(pair[0], pair[1]);
      if (res[0] != res[1]) {
        System.out.println(Arrays.toString(pair) + " - " + Arrays.toString(res));
      }
    }
  }

  @Test
  public void testMultiply() throws Exception {
    for (int i = 0; i < 100; i++) {
      int[] pair = RandomUtil.genRandomKNumbers(2, -20000, 20000);
      if (pair[0] * pair[1] != multiply(pair[0], pair[1])) {
        System.out.println(Arrays.toString(pair) + " * ");
      }
    }
  }

  @Test
  public void testNextNumberWithSameNumberOfOnes() throws Exception {
    assertEquals(0x31, nextNumberWithSameNumberOfOnes(0x2C));
    assertEquals(0xF000000000000000L, nextNumberWithSameNumberOfOnes(0xF000000000000000L));
  }

  @Test
  public void testNumberOfOnes() throws Exception {
    assertEquals(3, numberOfOnes(0xB));
  }

  @Test
  public void testPrevNumberWithSameNumberOfOnes() throws Exception {
    assertEquals(0x2C, prevNumberWithSameNumberOfOnes(0x31));
    assertEquals(0x2A, prevNumberWithSameNumberOfOnes(0x2C));
    assertEquals(1L, prevNumberWithSameNumberOfOnes(1L));
  }

  @Test
  public void testReverse() throws Exception {
    assertEquals(0x800000000000L, reverseBits(65536));
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
    assertEquals(0x13, setBits(0x1FL, 3, 2, 0));
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
