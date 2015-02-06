package com.shuoma.alg.bit;

/**
 * Count the Number of 1s in 32-bit Integer.
 */
public class CountOneInInteger {

  // ??
  static int count(int x) {
    x = ((x & 0xAAAA) >> 1) + (x & 0x5555);
    x = ((x & 0xCCCC) >> 2) + (x & 0x3333);
    x = ((x & 0xF0F0) >> 4) + (x & 0x0F0F);
    x = ((x & 0xFF00) >> 8) + (x & 0x00FF);
    return x;
  }
}
