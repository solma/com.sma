package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BitOperation;

import com.shuoma.annotation.Tag;

/**
 * Given an array of [1...n] with one number missing, find the missing number.
 */
@Tag(algs = BitOperation)
public class MissingElement {

  public static void main(String[] args) {
    System.out.println(missingElement(new int[] {1, 5, 6, 4, 7, 3}));
  }

  static int missingElement(int[] a) {
    int res = 0;
    for (int i = 0; i <= a.length; i++) {
      res ^= i + 1;
    }
    for (int i = 0; i < a.length; i++) {
      res ^= a[i];
    }
    return res;
  }
}
