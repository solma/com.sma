package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.sma.annotation.Tag.Algorithm.DepthFirstSearch;
import static com.sma.annotation.Tag.DataStructure.MatrixGraph;
import static com.sma.annotation.Tag.Reference.LeetCode;

/**
 Given an m x n matrix of non-negative integers representing the height of each unit cell in a
 continent, the "Pacific ocean" touches the left and top edges of the matrix and
 the "Atlantic ocean" touches the right and bottom edges.

 Water can only flow in four directions (up, down, left, or right) from a cell to
 another one with height equal or lower.

 Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

 Note:
 The order of returned grid coordinates does not matter.
 Both m and n are less than 150.
 Example:

 Given the following 5x5 matrix:

 Pacific ~   ~   ~   ~   ~
 ~  1   2   2   3  (5) *
 ~  3   2   3  (4) (4) *
 ~  2   4  (5)  3   1  *
 ~ (6) (7)  1   4   5  *
 ~ (5)  1   1   2   4  *
 *   *   *   *   * Atlantic

 Return:

 [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
 (positions with parentheses in above matrix).
 */
@Tag(algs = DepthFirstSearch, dss = MatrixGraph, references = LeetCode)
public class PacificAtlanticWaterFlow {
  public static void main(String[] args) {
    int[][][] cases = {
        {{3, 2, 1}, {4, 5, 1}, {5, 6, 1}, {6, 6, 6}},
        {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}}
    };
    for (int[][] matrix: cases) {
      List<int[]> res = new PacificAtlanticWaterFlow().pacificAtlantic(matrix);
      for (int[] row : res) {
        System.out.println(Arrays.toString(row));
      }
      System.out.println();
    }
  }

  public List<int[]> pacificAtlantic(int[][] matrix) {
    int m = matrix.length;
    if(m == 0) {
      return new ArrayList<>();
    }
    int n = matrix[0].length;
    boolean[][][] flag = new boolean[m][n][2];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        flag[i][j] = new boolean[2];
      }
    }

    // Pacific side;
    List<List<Cell>> banks = new LinkedList<>();
    banks.add(new LinkedList<Cell>());
    banks.add(new LinkedList<Cell>());

    for (int i = 0; i < m; i++) { banks.get(0).add(new Cell(i, 0, matrix[i][0])); }
    for (int j = 0; j < n; j++) { banks.get(0).add(new Cell(0, j, matrix[0][j])); }
    for (int j = 0; j < n; j++) { banks.get(1).add(new Cell(m - 1, j, matrix[m - 1][j])); }
    for (int i = 0; i < m; i++) { banks.get(1).add(new Cell(i, n - 1, matrix[i][n - 1])); }


    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    for (int bank = 0; bank < 2; bank++)
      for (Cell cur : banks.get(bank)) {
        if (!flag[cur.i][cur.j][bank])
          dfs(matrix, flag, dirs, cur.i, cur.j, bank, m, n);
      }

    List<int []> res = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (flag[i][j][0] && flag[i][j][1])
          res.add(new int[] {i, j});
      }
    }
    return res;
  }

  class Cell {
    int i, j, ele;

    Cell(int i, int j, int ele) {
      this.i = i;
      this.j = j;
      this.ele = ele;
    }
  }

  void dfs(int[][] elevations, boolean[][][] flag, int[][] dirs, int ci, int cj, int bank, int m, int n) {
    flag[ci][cj][bank] = true;
    for (int[] dir : dirs) {
      int i = ci + dir[0], j = cj + dir[1];
      //System.out.println("i=" + i + " j=" + j);
      if (i >= 0 && i < m && j >= 0 && j < n && !flag[i][j][bank] && elevations[ci][cj] <= elevations[i][j]) {
        dfs(elevations, flag, dirs, i, j, bank, m, n);
      }
    }
  }
}
