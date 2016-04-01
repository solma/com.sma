package com.sma.alg;
//ref: http://yyeclipse.blogspot.com/2012/11/solving-maximal-rectangle-problem-based.html
//ref: http://wansishuang.appspot.com/?p=38002
import static com.sma.alg.LargestRectangleInHistogram.largestRectangleArea;
import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = DynamicProgramming, dss = StackT, references = LeetCode)
public class MaximalRectangle {

  public int maximalRectangle(char[][] matrix) {
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
