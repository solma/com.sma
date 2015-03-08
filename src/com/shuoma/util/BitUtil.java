package com.shuoma.util;

import java.util.LinkedList;
import java.util.List;

public class BitUtil {

  public static void main (String[] args) {
    System.out.println(reverseBits(65536L));
  }

  /** Addition implementation using bit and comparison operators only. */
  public static long add(long x, long y) {
    long carry, sum;
    do {
      carry = x & y;
      sum = x ^ y;
      x = carry << 1;
      y = sum;
    } while (carry != 0);
    return sum;
  }

  /** Addition implementation using bit and comparison operators only. */
  public static long addAlterImpl(long x, long y) {
    long carry = 0, sum = 0;
    for (int i = 0; i < 64; i++) {
      int xBit = getBit(x, i), yBit = getBit(y, i);
      sum |= (xBit ^ yBit ^ carry) << i;
      carry = (xBit & yBit) | (xBit & carry) | (yBit & carry);
    }
    return sum;
  }

  /** Set ith bit to 0. */
  public static long clearBit(long n, int ith) {
    return setBits(n, ith, ith, 0);
  }

  /** Set ith bit to least significant bit to 0. */
  public static long clearBits(long n, int ith) {
    return n & (-1L << (ith + 1));
  }

  /** Set the lowest set bit to 0. */
  public static long clearLowestSetBit(long n) {
    return n & (n - 1);
  }

  /** Extract the lowest set bit. */
  public static long extractLowestSetBit(long n) {
    return n & ~(n - 1);
  }

  /** Division implementation using bit and comparison operators only. */
  public static long divide(long x, long y) {
    int sign = 0;
    if (x < 0) {
      x = add(~x, 1);
      sign ^= 1;
    }
    if (y < 0) {
      y = add(~y, 1);
      sign ^= 1;
    }

    List<Long> exponents = new LinkedList<>();
    long multiplier = 1, product;
    while ((product = multiply(y, multiplier)) <= x) {
      exponents.add(product);
      multiplier <<= 1;
    }

    long quotient = 0;
    if (y != 0) {
      for (int exponentIdx = exponents.size() - 1; exponentIdx >= 0; exponentIdx--) {
        long exponent = exponents.get(exponentIdx);
        if (x >= exponent) {
          x = minus(x, exponent);
          quotient += 1 << exponentIdx;
        }
      }
    }
    if (sign > 0) {
      quotient = add(~quotient, 1);
    }
    return quotient;
  }

  /** Flip ith bit. */
  public static long flipBit(long n, int ith) {
    n ^= 1 << ith;
    return n;
  }

  /** Get ith bit. */
  public static int getBit(long n, int ith) {
    return (int) (n >> ith & 1);
  }

  /** Check if a number is power of four. */
  public static boolean isPowerOfFour(long n) {
    return isPowerOfTwo(n) && 0 == (n & 0xAAAAAAAAAAAAAAAAL);
  }

  /** Check if a number is power of two. */
  public static boolean isPowerOfTwo(long n) {
    return 0 == (n & (n - 1));
  }

  /** Max number. */
  public static long maxWithoutComparisonOperator(long n, long m) {
    long diff = n - m;
    return n - (diff >> 63 & 1) * diff;
  }

  /** Subtraction implementation using bit and comparison operators only. */
  public static long minus(long x, long y) {
    return add(x, add(~y, 1));
  }

  /** Multiplication implementation using bit and comparison operators only. */
  public static long multiply(long x, long y) {
    if (x == 0 || y == 0)
      return 0;
    boolean negative = x < 0 ^ y < 0;
    if (x < 0) {
      x = add(~x, 1);
    }
    if (y < 0) {
      y = add(~y, 1);
    }
    long bit = 1, product = 0;
    while (x >= bit) {
      product = add((x & bit) == 0 ? 0 : y, product);
      y <<= 1;
      bit <<= 1;
    }
    return negative ? add(~product, 1) : product;
  }

  /** Get the smallest number that is larger and has the same number of 1's as n. */
  public static long nextNumberWithSameNumberOfOnes(long n) {
    return numberWithSameNumberOfOnes(n, true);
  }

  /** Get the largest number that is smaller and has the same number of 1's as n. */
  public static long prevNumberWithSameNumberOfOnes(long n) {
    return numberWithSameNumberOfOnes(n, false);
  }

  /** Reverse its bit representation. */
  public static long reverseBits(long n) {
    // int trailingZeros = Long.toBinaryString(value).length();
    n = swapOddAnEvenBits(n);
    n = ((n & 0x3333333333333333L) << 2) | ((n & 0xCCCCCCCCCCCCCCCCL) >>> 2);
    n = ((n & 0x0F0F0F0F0F0F0F0FL) << 4) | ((n & 0xF0F0F0F0F0F0F0F0L) >>> 4);
    n = ((n & 0x00FF00FF00FF00FFL) << 8) | ((n & 0xFF00FF00FF00FF00L) >>> 8);
    n = ((n & 0x0000FFFF0000FFFFL) << 16) | ((n & 0xFFFF0000FFFF0000L) >>> 16);
    n = (n << 32) | (n >>> 32);
    // value = value >> (64 - trailingZeros);
    return n;
  }

  /** Set ith bit to 1. */
  public static long setBit(long n, int ith) {
    return setBits(n, ith, ith, 1);
  }

  /** Set minTh ~ maxTh bit to given value. */
  public static long setBits(long n, int maxTh, int minTh, int val) {
    n &= ~(((int) Math.pow(2, maxTh - minTh + 1) - 1) << minTh);
    n |= val << minTh;
    return n;
  }

  /** Swap ith and jth bit. */
  public static long swapTwoBits(long n, int ith, int jth) {
    if (getBit(n, ith) == getBit(n, jth)) {
      return n;
    }
    return flipBit(flipBit(n, ith), jth);
  }

  /** Swap odd and even position bits. */
  public static long swapOddAnEvenBits(long n) {
    return ((n & 0x5555555555555555L) << 1) | ((n & 0xAAAAAAAAAAAAAAAAL) >>> 1);
  }

  static long numberWithSameNumberOfOnes(long n, boolean isLarger) {
    int oneCnt = 0;
    int skipBit = isLarger ? 0 : 1;
    for (int i = 0; i < 63; i++) {
      int curBit = getBit(n, i);
      if (curBit == skipBit) {
        oneCnt += curBit == 1 ? 1 : 0;
        continue;
      }
      if (getBit(n, i + 1) == skipBit) {
        n = swapTwoBits(n, i, i + 1);
        n = clearBits(n, i - 1);
        int shiftOffset = 0;
        if (!isLarger) {
          shiftOffset = i - oneCnt;
        }
        n |= ((1 << oneCnt) - 1) << shiftOffset;
        return n;
      }
      oneCnt += curBit == 1 ? 1 : 0;
    }
    return n;
  }
}
