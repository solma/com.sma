package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

@Tag(algs = DynamicProgramming)
public class DynamicProgrammingPlayground {
  public static void main(String[] args) {
    new DynamicProgrammingPlayground().main();
  }

  int nRow = 6, nCol = 8;

  public void main() {
    int[][] matrix = new int[nRow][nCol];
    dp1(matrix);
    print(matrix);
    reset(matrix);

    dp1With1DArray(matrix);
    print(matrix);
    Deque<Integer> deque = new LinkedList<>();
  }

  // matrix[i][j] = matrix[i-1][j-1] + matrix[i-1][j]
  public void dp1(int[][] matrix) {
    for (int j = 0; j < nCol; j++)
      matrix[0][j] = j;
    // for (int i = 0; i < nRow; i++) matrix[i][0] = i;
    for (int j = 1; j < nCol; j++)
      for (int i = 1; i < nRow; i++)
        matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
  }

  // matrix[i][j] = matrix[i-1][j-1] + matrix[i-1][j]
  public void dp1With1DArray(int[][] matrix) {
    for (int j = 0; j < nCol; j++) {
      matrix[0][j] = j;
    }
    // for (int i = 0; i < nRow; i++) matrix[i][0] = i;
    for (int i = 1; i < nRow; i++)
      for (int j = nCol - 1; j > 0; j--) {
        matrix[0][j] += matrix[0][j - 1];
      }
  }

  public void reset(int[][] matrix) {
    for (int i = 0; i < nRow; i++) {
      Arrays.fill(matrix[i], 0);
    }
    System.out.println();
  }

  public void print(int[][] matrix) {
    for (int i = 0; i < nRow; i++) {
      for (int j = 0; j < nCol; j++) {
        System.out.printf("%5d", matrix[i][j]);
      }
      System.out.println();
    }
  }
}
