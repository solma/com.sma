package com.shuoma.alg;

import com.shuoma.util.ArrayUtil;

public class SetMatrixZeros {

  public static void main(String[] args) {
    int[][] matrix = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
    ArrayUtil.print(matrix);
    new SetMatrixZeros().setZeroes(matrix);
    ArrayUtil.print(matrix);
  }

  //use the row and col of first zero to store the mark.
  //check if row == -1 after a full scan, directly return
  //check i != row and j != col when do reset in second stage.
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    if (m == 0) { return; }
    int n = matrix[0].length;
    int row = -1;
    int col = -1;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] != 0) { continue; }
        if (row == -1) {
          row = i;
          col = j;
        } else {
          matrix[row][j] = 0;
          matrix[i][col] = 0;
        }
      }
    }
    if (row == -1) { return; }

    for (int i = 0; i < m; i++) {
      if (i == row || matrix[i][col] != 0) { continue; }
      for (int j = 0; j < n; j++) {
        matrix[i][j] = 0;
      }
    }

    for (int j = 0; j < n; j++) {
      if (j == col || matrix[row][j] != 0) { continue; }
      for (int i = 0; i < m; i++) {
        matrix[i][j] = 0;
      }
    }

    for(int i = 0; i < m; i++) { matrix[i][col] = 0; }
    for(int j = 0; j < n; j++) { matrix[row][j] = 0; }
  }
}
