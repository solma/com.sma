package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Algorithm.Math;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Complexity.Linear;
import static com.sma.annotation.Tag.Complexity.Quadratic;

import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 Given a positive integer n, break it into the sum of at least two positive integers and
 maximize the product of those integers. Return the maximum product you can get.
 For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 Note: you may assume that n is not less than 2.
 */

@Tag(algs = {DynamicProgramming, Math}, dss = Array,
    timecomplexity = {Linear, Quadratic}, references = LeetCode)
public class IntegerBreak {

  public static void main(String[] args) {
    new IntegerBreak().main();
  }

  public void main() {
    for (int n = 2; n < 20; n++)
      System.out.println(n + ":" + integerBreakMath(n));
  }

  /**
   * basic dp
   */
  public long integerBreakDp(int n) {
    if (n < 2)
      return -1;
    long[] dp = new long[n + 1];
    dp[1] = 1;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= i - 1; j++)
        dp[i] = max(dp[i], max(dp[j], j) * (i - j));
      System.out.println(i + ":" + dp[i]);
    }
    return dp[n];
  }

  /**
   * Decompose n into 3's as many as possible (except 4 is divided into 2, 2)
   */
  public long integerBreakMath(int n) {
    if (n < 2) return -1;
    if (n < 4) return n - 1;

    int x, y;
    x = y = 0;
    switch (n % 3) {
      case 0:
        x = 0;
        y = n / 3;
        break;
      case 1:
        x = 2;
        y = (n - 4) / 3;
        break;
      case 2:
        x = 1;
        y = (n - 2) / 3;
        break;
    }
    return (long) (pow(2, x) * pow(3, y));
  }
}
