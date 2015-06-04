package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitManipulation;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BitManipulation, reference = LeetCode)
public class ReverseBits {

  public static void main(String[] args) {
    System.out.println(reverseBits(65536));
  }

  // you need treat n as an unsigned value
  public static int reverseBits(int n) {
    n = ((n & 0x55555555) << 1) | ((n & 0xAAAAAAAA) >>> 1);
    n = ((n & 0x33333333) << 2) | ((n & 0xCCCCCCCC) >>> 2);
    n = ((n & 0x0F0F0F0F) << 4) | ((n & 0xF0F0F0F0) >>> 4);
    n = ((n & 0x00FF00FF) << 8) | ((n & 0xFF00FF00) >>> 8);
    n = (n << 16) | (n >>> 16);
    return n;
  }
}
