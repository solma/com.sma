package com.shuoma.alg.bit;

/**
 * Count the Number of 1s in 32-bit Integer.
 */
public class CountOneInInteger {

  public static void main(String[] args) {
    count(220000);
  }

  static int count(int x) {
    System.out.println(x + " " + Integer.toBinaryString(x));
    x = ((x & 0xAAAA) >> 1) + (x & 0x5555);
    System.out.println(x + " " + Integer.toBinaryString(x));
    x = ((x & 0xCCCC) >> 2) + (x & 0x3333);
    System.out.println(x + " " + Integer.toBinaryString(x));
    x = ((x & 0xF0F0) >> 4) + (x & 0x0F0F);
    System.out.println(x + " " + Integer.toBinaryString(x));
    x = ((x & 0xFF00) >> 8) + (x & 0x00FF);
    System.out.println(x + " " + Integer.toBinaryString(x));
    return x;
  }
}
