package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BottomUp;
import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(algs = {BottomUp, DynamicProgramming}, references = LeetCode) public class Triangle {
  public static void main(String[] args) {
    new Triangle().main();
  }

  public void main() {
    List<List<Integer>> triangle = new ArrayList<>();
    int[][] arr = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
    for (int i = 0; i < arr.length; i++) {
      triangle.add(new ArrayList<Integer>());
      for (int j = 0; j < arr[i].length; j++)
        triangle.get(i).add(arr[i][j]);
    }
    System.out.println(minimumTotal(triangle));
  }

  //second pass
  public int minimumTotal(List<List<Integer>> triangle) {
    int ret = Integer.MAX_VALUE;
    int n = triangle.size();
    if (n == 0) { return ret; }
    if (n == 1) {
      if (triangle.get(0).size() == 0) { return ret; } else { return triangle.get(0).get(0); }
    }

    for (int i = 1; i < triangle.size(); i++) {
      List<Integer> row = triangle.get(i);
      List<Integer> prevRow = triangle.get(i - 1);
      for (int j = 0; j < row.size(); j++) {
        if (j == 0) { row.set(j, row.get(j) + prevRow.get(j)); } else if (j == row.size() - 1)
          row.set(j, row.get(j) + prevRow.get(j - 1));
        else
          row.set(j, row.get(j) + Math.min(prevRow.get(j), prevRow.get(j - 1)));
      }
      ret = Integer.MAX_VALUE;
      for (int j = 0; j < row.size(); j++) {
        ret = Math.min(ret, row.get(j));
      }
      //System.out.println(ret);
    }
    return ret;
  }


  //dp
  public int minimumTotalDp(List<List<Integer>> triangle) {
    for (int i = triangle.size() - 2; i >= 0; i--) {
      for (int j = 0; j < triangle.get(i).size(); j++) {
        triangle.get(i).set(j, triangle.get(i).get(j) + Math
            .min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
      }
    }
    return triangle.get(0).get(0);
  }


  //recursion
  // public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
  // int[] min=new int[1];
  // min[0]=Integer.MAX_VALUE;
  // if(triangle.size()>0) minPath(triangle, min, 0, 0, triangle.get(0).get(0));
  // return min[0];
  // }

  // public void minPath(ArrayList<ArrayList<Integer>> triangle, int[] min, int row, int col, int sum){
  // if(row==triangle.size()-1){
  // //System.out.println(row+" "+col+" "+sum);
  // if(sum<min[0]) min[0]=sum;
  // return;
  // }
  // for(int i=col;i<=col+1;i++){
  // if(i<triangle.get(row+1).size()){
  // minPath(triangle, min, row+1, i, sum+triangle.get(row+1).get(i) );
  // }
  // }
  // }
}
