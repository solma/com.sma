package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.MatrixGraph;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;
import com.shuoma.util.RandomUtil;

/**
 * Given a matrix, each cell having only 0's or 1's, find the largest sub matrix with equal number of 0's and 1's in it.
 */
@Tag(algs = DynamicProgramming, dss = {MatrixGraph, Subarray})
public class MaxRectangleWithEqualZerosAndOnes {
  public static void main(String[] args) {
    int[][] board = RandomUtil.genRandomMatrix(6, 7, 1, false, false);
    ArrayUtil.print(board);

    int[][] rectangle = maxRectangle(board);
    System.out.println();
    ArrayUtil.print(rectangle);
    System.out.println();
    ArrayUtil.print(board, rectangle[0][0], rectangle[1][0], rectangle[0][1], rectangle[1][1], 5);
  }

  static int[][] maxRectangle(int[][] board) {
    int m = board.length;
    int n = board[0].length;

    int[][] rectangle = new int[2][2];
    int maxArea = 0;
    for (int i = 0; i < m; i++) {
      int[] sum = new int[n];
      for (int j = i; j < m; j++) {
        for (int k = 0; k < n; k++)
          sum[k] += board[j][k];
        //System.out.println(i + " " + j + " " + Arrays.toString(sum));

        int[] accuSum = new int[n];
        for (int k = 0; k < n; k++)
          accuSum[k] = sum[k] + (k > 0 ? accuSum[k - 1] : 0);
        //System.out.println(i + " " + j + " " + Arrays.toString(accuSum));

        // column p ~ q inclusive
        for (int p = 0; p < n; p++) {
          for (int q = p; q < n; q++) {
            int area = (q - p + 1) * (j - i + 1) / 2;
            if (area > maxArea && accuSum[q] - (p > 0 ? accuSum[p - 1] : 0) == area) {
              rectangle[0][0] = i;
              rectangle[0][1] = p;
              rectangle[1][0] = j;
              rectangle[1][1] = q;
              maxArea = area;
            }
          }
        }
      }
    }
    return rectangle;
  }
}
