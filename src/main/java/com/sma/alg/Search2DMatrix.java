/*
Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.


Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
 */
package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.BinarySearch;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;

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
      return R != m && matrix[rowIdx][R] == target;
    } else {
      return true;
    }

  }
}
