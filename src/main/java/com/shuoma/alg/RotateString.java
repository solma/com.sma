package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;
import com.shuoma.util.MathUtil;

/** Rotate left shift a string. */
@Tag(dl = D3, dss = String, tricks = InplaceSwap)
public class RotateString {

  public static void main(String[] args) {
    System.out.println(rotate("123", 1, true));
  }

  static String rotate(String s, int m, boolean rotateLeft) {
    char[] str = s.toCharArray();
    int len = str.length;
    m %= len;
    if (m == 0 || len == 1) return s;

    int numOfCycles = MathUtil.gcd(len, m);
    int cycleSize = len / numOfCycles;

    if (rotateLeft) {
      for (int j = 0; j < numOfCycles; j++) {
        char tmp = str[j];
        int i;
        for (i = 0; i < cycleSize - 1; i++) {
          str[(j + i * m) % len] = str[(j + (i + 1) * m) % len];
        }
        str[(j + i * m) % len] = tmp;
      }
    } else {
      for (int j = 0; j < numOfCycles; j++) {
        char tmp = str[(j + (cycleSize - 1) * m) % len];
        int i;
        for (i = cycleSize - 1; i > 0; i--) {
          str[(j + i * m) % len] = str[(j + (i - 1) * m) % len];
        }
        str[(j + i * m) % len] = tmp;
      }
    }
    return new String(str);
  }
}
