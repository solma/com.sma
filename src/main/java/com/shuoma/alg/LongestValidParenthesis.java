package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, references = LeetCode)
public class LongestValidParenthesis {

  //O(n)
  public int longestValidParentheses(String s) {
    int n = s.length();
    int[] dp = new int[n];
    int max = 0;
    for (int i = n - 2; i >= 0; i--) {
      if (s.charAt(i) == '(') {
        int j = i + 1 + dp[i + 1];  //longest valid parenthesis starting from i+1
        if (j < n && s.charAt(j) == ')') {
          dp[i] = dp[i + 1] + 2;
          //add longest valid parenthesis starting from j+1
          dp[i] += j + 1 < n ? dp[j + 1] : 0;
        }
        max = Math.max(max, dp[i]);
      }
    }
    return max;
  }
}
