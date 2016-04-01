package com.sma.alg;

import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(references = LeetCode)
public class FactorialTrailingZeros {

  public static int trailingZeroes(int n) {
    int cnt = 0;
    while (n > 0) {
      n /= 5;
      cnt += n;
    }
    return cnt;
  }
}
