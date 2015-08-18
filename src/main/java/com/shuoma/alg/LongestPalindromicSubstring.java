package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Substring;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D3, dss = Substring, references = LeetCode)
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
      for (int l = 0; l <= n - len; l++) {
        if ((len == 2 || isPalindrome[l + 1][l + len - 1])
            && s.charAt(l) == s.charAt(l + len - 1)) {
          isPalindrome[l][l + len] = true;
          idx[0] = l;
          idx[1] = l + len;
        }
      }
    }
    return s.substring(idx[0], idx[1]);
  }
}
