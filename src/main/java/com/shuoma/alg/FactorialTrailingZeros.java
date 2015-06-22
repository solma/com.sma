package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

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
