package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Arithmetic, references = LeetCode)
public class ReverseInteger {
  public int reverse(int x) {
    boolean flag = false;
    if (x < 0) {
      x = 0 - x;
      flag = true;
    }

    long res = 0;
    while (x > 0) {
      int mod = x % 10;
      res = res * 10 + mod;
      x /= 10;
    }

    if (flag) { res = 0 - res; }

    if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) { return 0; }
    return (int) res;
  }
}
