package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Substring;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Complexity.Quadratic;

/**
 Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000,
 and there exists one unique longest palindromic substring.
 */
@Tag(algs = DynamicProgramming, dl = D3, dss = Substring,
    timecomplexity = Quadratic, references = LeetCode)
public class LongestPalindromicSubstring {

  public String longestPalindrome(String s) {
    int n = s.length();
    if (n == 0) { return null; }
    int[] idx = {0, 1};
    boolean[][] isPalindrome = new boolean[n + 1][];
    for (int i = 0; i < n; i++) {
      isPalindrome[i] = new boolean[n + 1];
      isPalindrome[i][i + 1] = true;
    }

    for (int len = 2; len <= n; len++) {
      for (int i = 0; i <= n - len; i++) {
        if ((len == 2 || isPalindrome[i + 1][i + len - 1])
            && s.charAt(i) == s.charAt(i + len - 1)) {
          isPalindrome[i][i + len] = true;
          idx[0] = i;
          idx[1] = i + len;
        }
      }
    }
    return s.substring(idx[0], idx[1]);
  }
}
