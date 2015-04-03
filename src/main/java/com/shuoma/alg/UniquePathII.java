package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dss = Subarray, source = LeetCode)
public class UniquePathII {
  public static void main(String[] args) {
    UniquePathII ins = new UniquePathII();
    int n = 10;
    int[][] map = new int[n][n];

    System.out.println(ins.uniquePathsWithObstacles(map));
  }

  //DP
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int n = obstacleGrid.length;
    int m = obstacleGrid[0].length;
    int[] dp = new int[m + 1];

    if (obstacleGrid[0][0] != 1)
      dp[1] = 1;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        dp[j + 1] = obstacleGrid[i][j] == 1 ? 0 : dp[j + 1] + dp[j];
      }
    }

    return dp[m];
  }


  //TLE Recursion
  public int uniquePathsWithObstaclesTLE(int[][] obstacleGrid) {
    int nRow = obstacleGrid.length;
    if (nRow == 0)
      return 0;
    int nCol = obstacleGrid[0].length;
    if (obstacleGrid[nRow - 1][nCol - 1] == 1)
      return 0;

    int[] res = new int[1];
    uniquePathsWithObstacles(obstacleGrid, res, 0, 0, nRow, nCol);
    return res[0];
  }

  public void uniquePathsWithObstacles(int[][] obstacleGrid, int[] res, int row, int col, int nRow, int nCol) {
    //System.out.println(row+" "+col);
    if (row == nRow - 1 && col == nCol - 1) {
      res[0] += 1;
      return;
    }
    if (row < 0 || col < 0 || row == nRow || col == nCol || obstacleGrid[row][col] != 0)
      return;
    obstacleGrid[row][col] = -1;
    uniquePathsWithObstacles(obstacleGrid, res, row + 1, col, nRow, nCol);
    uniquePathsWithObstacles(obstacleGrid, res, row, col + 1, nRow, nCol);
    obstacleGrid[row][col] = 0;
  }
}
