package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Complexity.Quadratic;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

@Tag(algs = DynamicProgramming, timecomplexity = Quadratic,
    dss = Array, references = LeetCode)
public class MinimumPathSum {
  public static void main(String[] args) {
    int[][] grid = new int[][] {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    new MinimumPathSum().minPathSum(grid);
  }

  public int minPathSum(int[][] grid) {
    int n = grid.length;
    if (n < 1) { return -1; }
    int m = grid[0].length;

    int[] res = new int[m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (j > 0 && (res[j - 1] < res[j] || i == 0))
          res[j] = res[j - 1] + grid[i][j];
        else
          res[j] += grid[i][j];
      }
      for (int k = 0; k < m; k++) { System.out.print(res[k] + "  "); }
      System.out.println();
    }
    return res[m - 1];
  }
}
