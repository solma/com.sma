package algorithm;

import java.util.Deque;
import java.util.LinkedList;

public class DynamicProgramming {
  public static void main(String[] args) {
    new DynamicProgramming().main();
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
    for (int i = 1; i < nRow; i++)
      for (int j = 1; j < nCol; j++)
        matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
  }

  // matrix[i][j] = matrix[i-1][j-1] + matrix[i-1][j]
  public void dp1With1DArray(int[][] matrix) {
    for (int j = 0; j < nCol; j++)
      matrix[0][j] = j;
    // for (int i = 0; i < nRow; i++) matrix[i][0] = i;
    for (int i = 1; i < nRow; i++)
      for (int j = nCol - 1; j > 0; j--)
        matrix[0][j] = matrix[0][j - 1] + matrix[0][j];
  }

  public void reset(int[][] matrix) {
    for (int i = 0; i < nRow; i++)
      for (int j = 0; j < nCol; j++)
        matrix[i][j] = 0;
    System.out.println();
  }

  public void print(int[][] matrix) {
    for (int i = 0; i < nRow; i++) {
      for (int j = 0; j < nCol; j++)
        System.out.printf("%5d", matrix[i][j]);
      System.out.println();
    }
  }
}
