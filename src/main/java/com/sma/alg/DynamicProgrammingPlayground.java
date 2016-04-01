package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;

import com.sma.annotation.Tag;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

@Tag(algs = DynamicProgramming)
public class DynamicProgrammingPlayground {
  public static void main(String[] args) {
    new DynamicProgrammingPlayground().main();
  }

  int nRow = 3, nCol = 4;

  int offset = 2;

  public void main() {
    int[][] matrix = new int[nRow][nCol];
    colMajor(matrix);
    print(matrix);
    reset(matrix);

    rowMajor1D(matrix);
    print(matrix);
    Deque<Integer> deque = new LinkedList<>();
  }

  // matrix[i][j] = matrix[i-1][j-1] + matrix[i-1][j]
  public void colMajor(int[][] matrix) {
    for (int j = 0; j < nCol; j++)
      matrix[0][j] = j;
    // for (int i = 0; i < nRow; i++) matrix[i][0] = i;
    for (int j = 1; j < nCol; j++)
      for (int i = 1; i < nRow; i++) {
        matrix[i][j] = matrix[i - 1][j];
        if (j >= offset) {
          matrix[i][j] += matrix[i - 1][j - offset];
        }
      }
  }

  // matrix[i][j] = matrix[i-1][j-1] + matrix[i-1][j]
  public void rowMajor1D(int[][] matrix) {
    for (int j = 0; j < nCol; j++) {
      matrix[0][j] = j;
    }
    // for (int i = 0; i < nRow; i++) matrix[i][0] = i;
    for (int i = 1; i < nRow; i++)
      for (int j = nCol - 1; j > 0; j--) {
        if (j >= offset) {
          matrix[0][j] += matrix[0][j - offset];
        }
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
