package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = DynamicProgramming, references = LeetCode)
public class LongestValidParenthesis {

  //O(n)
  public int longestValidParentheses(String s) {
    int n = s.length();
    int[] dp = new int[n];
    int max = 0;
    for (int i = n - 2; i >= 0; i--) {
      if (s.charAt(i) == ')') continue;

      // if s[i + 1] == ')' then dp[i + 1] = 0;
      // else i + 1 + dp[i + 1] points to the furthest ')' that matches the current '('
      int j = i + 1 + dp[i + 1];
      if (j < n && s.charAt(j) == ')') {
        dp[i] = dp[i + 1] + 2;
        //add longest valid parenthesis starting from j+1
        dp[i] += j + 1 < n ? dp[j + 1] : 0;
      }
      max = Math.max(max, dp[i]);
    }
    return max;
  }
}
