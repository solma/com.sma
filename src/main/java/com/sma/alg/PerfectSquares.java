package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
@Tag(algs = {DynamicProgramming}, references = LeetCode)
public class PerfectSquares {
  public int numSquares(int n) {
    int nRoot = (int) Math.sqrt(n);
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= nRoot; j++) {
        if (i < j * j) { continue; }
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        if (dp[i] == 1) { break; }
      }
    }
    return dp[n];
  }
}
