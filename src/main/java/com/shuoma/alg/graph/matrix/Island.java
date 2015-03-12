package com.shuoma.alg.graph.matrix;

import static com.shuoma.util.ArrayUtil.inBoundary;

import com.shuoma.util.ArrayUtil;
import com.shuoma.util.RandomUtil;

/** Find out islands in matrix. */
public class Island {

  public static void main(String[] args) {
    Island ins = new Island();
    int[][] matrix = RandomUtil.genRandomMatrix(5, 5, 1, false, false);
    //ArrayUtil.print(matrix);
    ins.getIslands(matrix);
    ArrayUtil.print(matrix);
  }

  void getIslands(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int flag = 9;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 1) {
          dfs(matrix, i, j, flag--);
        }
      }
    }
  }

  void dfs(int[][] matrix, int i, int j, int flag) {
    matrix[i][j] = flag;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int[] dir : dirs) {
      int r = i + dir[0], c = j + dir[1];
      if (inBoundary(matrix, r, c) && matrix[r][c] == 1) {
        dfs(matrix, r, c, flag);
      }
    }
  }
}
