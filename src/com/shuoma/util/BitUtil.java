package com.shuoma.util;

public class BitUtil {

  /** Set ith bit to 0. */
  public static long clearBit(long n, int ith) {
    return setBits(n, ith, ith, 0);
  }

  /** Set ith bit to least significant bit to 0. */
  public static long clearBits(long n, int ith) {
    return n & (-1L << (ith + 1));
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
    n = ((n & 0x3333333333333333L) << 2) | ((n & 0xCCCCCCCCCCCCCCCCL) >> 2);
    n = ((n & 0x0F0F0F0F0F0F0F0FL) << 4) | ((n & 0xF0F0F0F0F0F0F0F0L) >> 4);
    n = ((n & 0x00FF00FF00FF00FFL) << 8) | ((n & 0xFF00FF00FF00FF00L) >> 8);
    n = ((n & 0x0000FFFF0000FFFFL) << 16) | ((n & 0xFFFF0000FFFF0000L) >> 16);
    n = (n << 32) | (n >> 32);
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
    return ((n & 0x5555555555555555L) << 1) | ((n & 0xAAAAAAAAAAAAAAAAL) >> 1);
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
