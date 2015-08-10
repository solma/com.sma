package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.MatrixGraph;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D2, dss = MatrixGraph, references = LeetCode)
public class DungeonGame {

  // dp[i][j]: min health value at cell (i, j) after collecting
  int calculateMinimumHP(int[][] dungeon) {
    int m = dungeon.length;
    assert (m > 0);
    int n = dungeon[0].length;
    int[] dp = new int[n + 1];
    for (int j = n - 1; j >= 0; j--) dp[j] = Integer.MAX_VALUE;
    dp[n] = 1;

    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--)
        dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - dungeon[i][j]);
      //System.out.println(Arrays.toString(dp));
      dp[n] = Integer.MAX_VALUE;
    }
    return dp[0];
  }
}
