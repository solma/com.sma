package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BinarySearch;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;
import com.sma.annotation.Tag;

@Tag(algs = BinarySearch, dss = Array, references = LeetCode, tricks = TwoOrMorePointers)
public class Search2DMatrix {
  //second pass
  public boolean searchMatrix(int[][] matrix, int target) {
    int n = matrix.length;
    if (n == 0)
      return false;
    int m = matrix[0].length;
    if (m == 0)
      return false;
    int i = 0, j = m - 1;
    while (i < n && j >= 0) {
      if (matrix[i][j] == target)
        return true;
      else {
        if (matrix[i][j] < target)
          i++;
        else
          j--;
      }
    }
    return false;
  }

  //first pass
  public boolean searchMatrixFirstPass(int[][] matrix, int target) {
    int n = matrix.length;
    if (n == 0)
      return false;
    int m = matrix[0].length;
    if (m == 0)
      return false;

    int[] firstCol = new int[n];
    for (int i = 0; i < n; i++)
      firstCol[i] = matrix[i][0];

    int L = -1, R = n;
    while (L + 1 != R) {
      int M = L + (R - L) / 2;
      if (firstCol[M] < target)
        L = M;
      else
        R = M;
    }
    if (R == n || firstCol[R] != target) {
      int rowIdx = L;
      if (rowIdx == -1)
        return false;
      L = -1;
      R = m;
      while (L + 1 != R) {
        int M = L + (R - L) / 2;
        if (matrix[rowIdx][M] < target)
          L = M;
        else
          R = M;
      }
      if (R == m || matrix[rowIdx][R] != target)
        return false;
      return true;
    } else {
      return true;
    }

  }
}
