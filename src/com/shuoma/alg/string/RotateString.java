package com.shuoma.alg.string;

import com.shuoma.util.MathUtil;

/** Rotate left shift a string. */
public class RotateString {

  public static void main(String[] args) {
    System.out.println(rotate("abcdefg", 6));
  }

  static String rotate(String s, int m) {
    char[] str = s.toCharArray();
    int len = str.length;

    int numOfCycles = MathUtil.gcd(len, m);
    int cycleSize = len / numOfCycles;
    for (int j = 0; j < numOfCycles; j++) {
      char tmp = str[j];
      int i;
      for (i = 0; i < cycleSize - 1; i++) {
        str[(j + i * m) % len] = str[(j + (i + 1) * m) % len];
      }
      str[(j + i * m) % len] = tmp;
    }
    return String.valueOf(str);
  }
}
