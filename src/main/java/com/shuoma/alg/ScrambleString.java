package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D3, dss = Subarray, references = LeetCode)
//ref http://www.blogjava.net/sandy/archive/2013/05/22/399605.html
public class ScrambleString {

  public boolean isScramble(String s1, String s2) {
    int m = s1.length();
    if (m != s2.length())
      return false;

    boolean[][][] dp = new boolean[m][m][m + 1];
    for (int i = m - 1; i >= 0; i--)
      for (int j = m - 1; j >= 0; j--)
        for (int k = 1; k <= m - Math.max(i, j); k++) {
          if (s1.substring(i, i + k).equals(s2.substring(j, j + k)))
            dp[i][j][k] = true;
          else {
            for (int l = 1; l < k; l++) {
              if ((dp[i][j][l] && dp[i + l][j + l][k - l])
                  || (dp[i][j + k - l][l] && dp[i + l][j][k - l])) {
                dp[i][j][k] = true;
                break;
              }
            }
          }
        }
    return dp[0][0][m];
  }
}
