package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D3, dss = Subarray, source = LeetCode)
public class DecodeWays {

  public int numDecodings(String s) {
    if (s == null || s.length() == 0)
      return 0;

    int len = s.length();
    int[] dp = new int[len + 1];

    dp[len] = 1;
    for (int i = len - 1; i >= 0; i--) {
      if (s.charAt(i) != '0') {
        dp[i] = dp[i + 1];
        if (i < len - 1 && Integer.parseInt(s.substring(i, i + 2)) <= 26)
          dp[i] += dp[i + 2];
      }
    }
    return dp[0];
  }
}
