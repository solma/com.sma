package com.shuoma.alg;
//ref: http://yyeclipse.blogspot.com/2012/11/solving-maximal-rectangle-problem-based.html
//ref: http://wansishuang.appspot.com/?p=38002
import static com.shuoma.alg.LargestRectangleInHistogram.largestRectangleArea;
import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.StackT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.util.StringUtil.toCharMatrix;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dss = StackT, references = LeetCode)
public class MaximalSquare {

  public static void main(String[] args) {
    String[] matrix = {"10100", "10111", "11111", "10010"};
    //String[] matrix = {"110", "111"};
    System.out.println(new MaximalSquare().maximalSquare(toCharMatrix(matrix)));
  }

  public int maximalSquare(char[][] matrix) {
    int maxArea = 0;
    int n = matrix.length;
    if (n < 1) {
      return maxArea;
    }
    int m = matrix[0].length;
    int[] height = new int[m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
      }
      maxArea = Math.max(maxArea, largestRectangleArea(height));
    }
    return maxArea;
  }
}
