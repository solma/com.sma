package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.MatrixGraph;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D2, dss = MatrixGraph, source = LeetCode)
public class DungeonGame {
  public static void main(String[] args) {
    int[][] dungeon = {
        {-2, -3, 3},
        {-5, -10, 1},
        {10, 30, -5}
    };
    System.out.println(calculateMinimumHP(dungeon));
  }

  public static int calculateMinimumHP(int[][] dungeon) {
    int n = dungeon.length;
    assert (n > 0);
    int m = dungeon[0].length;
    int[] dp = new int[m + 1];
    for (int j = m - 1; j >= 0; j--) dp[j] = Integer.MAX_VALUE;
    dp[m] = 1;

    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--)
        dp[j] = Math.max(1, Math.min(dp[j], dp[j + 1]) - dungeon[i][j]);
      //System.out.println(Arrays.toString(dp));
      dp[m] = Integer.MAX_VALUE;
    }
    return dp[0];
  }
}
