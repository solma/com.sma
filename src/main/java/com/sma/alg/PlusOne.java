package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = Arithmetic, references = LeetCode)
public class PlusOne {
  public int[] plusOne(int[] digits) {
    int n = digits.length;
    int[] res = new int[n];
    int carry = 1;
    for (int i = n - 1; i >= 0; i--) {
      res[i] = digits[i] + carry;
      carry = res[i] > 9 ? 1 : 0;
      res[i] %= 10;
    }

    if (carry > 0) {
      int[] ret = new int[n + 1];
      ret[0] = carry;
      for (int i = 0; i < n; i++)
        ret[i + 1] = res[i];
      return ret;
    }
    return res;
  }
}
