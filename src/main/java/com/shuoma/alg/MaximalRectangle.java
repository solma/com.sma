package com.shuoma.alg;
//ref: http://yyeclipse.blogspot.com/2012/11/solving-maximal-rectangle-problem-based.html
//ref: http://wansishuang.appspot.com/?p=38002
import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Stack;

@Tag(algs = DynamicProgramming, dss = Stack, references = LeetCode)
public class MaximalRectangle {

  //second pass
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

  int largestRectangleArea(int[] height) {
    int n = height.length;
    Stack<Integer> hStc = new Stack<>();
    Stack<Integer> wStc = new Stack<>();
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      if (hStc.isEmpty() || hStc.peek() <= height[i]) {
        hStc.push(height[i]);
        wStc.push(i);
      } else {
        int lastWidth = 0;
        while (!hStc.isEmpty() && hStc.peek() > height[i]) {
          lastWidth = wStc.pop();
          maxArea = Math.max(maxArea, hStc.pop() * (i - lastWidth));
        }
        hStc.push(height[i]);
        wStc.push(lastWidth);
      }
    }
    while (!hStc.isEmpty()) {
      maxArea = Math.max(maxArea, hStc.pop() * (n - wStc.pop()));
    }
    return maxArea;
  }

  //first pass
  // public int maximalRectangle(char[][] matrix) {
  // int n=matrix.length;
  // if(n==0) return 0;
  // int m=matrix[0].length;
  // if(m==0) return 0;
  // int[] H=new int[m], L=new int[m], R=new int[m];
  // for(int i=0;i<m;i++) R[i]=m-1;
  // int ret=0;
  // for(int i=0;i<n;i++){
  // int left=0, right=m-1;
  // //cal L from left to right
  // for(int j=0;j<m;j++){
  // if(matrix[i][j]=='1'){
  // H[j]++;
  // L[j]=Math.max(L[j], left);
  // }else{
  // left=j+1;
  // H[j]=0; L[j]=0; R[j]=m-1;
  // }
  // System.out.println(H[j]+" "+L[j]+" "+R[j]);
  // }

  // //cal R from right to left
  // for(int j=m-1; j>=0; j--){
  // if(matrix[i][j]=='1'){
  // R[j]=Math.min(R[j], right);
  // System.out.println(H[j]+" "+L[j]+" "+R[j]);
  // ret=Math.max(ret, H[j]*(R[j]-L[j]+1));
  // }else{
  // right=j-1;
  // }
  // }
  // }
  // return ret;
  // }
}
