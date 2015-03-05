package com.shuoma.util;

public class BitUtil {

  /** Set ith bit to 0. */
  public static long clearBit(long n, int ith) {
    return set(n, ith, ith, 0);
  }

  /** Flip ith bit. */
  public static long flipBit(long n, int ith) {
    n ^= 1 << ith;
    return n;
  }

  /** Get ith bit. */
  public static long getBit(long n, int ith) {
    return n >> ith & 1;
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
    if (isPowerOfTwo(n)) {
      return n << 1;
    }
    return n;
  }

  /** Reverse its bit representation. */
  public static long reverse(long n) {
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

  /** Set minTh ~ maxTh bit to given value. */
  public static long set(long n, int maxTh, int minTh, int val) {
    n &= ~(((int) Math.pow(2, maxTh - minTh + 1) - 1) << minTh);
    n |= val << minTh;
    return n;
  }

  /** Set ith bit to 1. */
  public static long setBit(long n, int ith) {
    return set(n, ith, ith, 1);
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
}
