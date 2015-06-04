package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D2, dss = Subarray, reference = LeetCode)
public class EditDistance {
  public static void main(String[] args) {
    System.out.println(new EditDistance().minDistance("ab", "bc"));
  }

  //first pass
  public int minDistance(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();
    if (n == 0)
      return m;
    if (m == 0)
      return n;
    int[][] dis = new int[n + 1][m + 1];

    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < m + 1; j++) {
        if (i > 0 && j > 0) {
          dis[i][j] = Math.min(dis[i - 1][j], dis[i][j - 1]) + 1;
          if (word1.charAt(i - 1) != word2.charAt(j - 1))
            dis[i][j] = Math.min(dis[i][j], dis[i - 1][j - 1] + 1);
          else
            dis[i][j] = Math.min(dis[i][j], dis[i - 1][j - 1]);
        } else {
          if (i > 0)
            dis[i][j] = dis[i - 1][j] + 1;
          if (j > 0)
            dis[i][j] = dis[i][j - 1] + 1;
        }
      }
    }
    return dis[n][m];
  }
}
