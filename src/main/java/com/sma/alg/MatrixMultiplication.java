package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Difficulty.D3;

import com.sma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D3, dss = Array)
public class MatrixMultiplication {
  public static void main(String[] args) {
    new MatrixMultiplication().main();
  }

  public void main() {
    int[] p = {10, 30, 20, 10, 5, 25, 15};
    System.out.println(count(p));
  }

  // another solution below
  static class Result {
    int cost;
    String combination;
    @Override
    public String toString() {
      return combination;
    }
  }

  public Result count(int[] dim) {
    int N = dim.length;
    int[][] opt = new int[N][N];
    int[][] sol = new int[N][N];

    for (int l = 2; l <= N - 1; l++) { // l is the length of matrix chain, i.e. no. of matrix
      for (int i = 1; i <= N - l; i++) { // i is the start idx of the matrix chain
        int j = i + l - 1; // j is the end idx of the matrix chain
        opt[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          int tmp = opt[i][k] + opt[k + 1][j] + dim[i - 1] * dim[k] * dim[j];
          if (tmp < opt[i][j]) {
            opt[i][j] = tmp;
            sol[i][j] = k;
          }
        }
      }
    }

    Result result = new Result();
    result.cost = opt[1][N - 1];
    result.combination = buildCombination(N, sol);
    return result;
  }

  String addBracket(String str, int i, int j, int k, int[][] sol) {
    if (str.length() <= 2) return str;
    String a = str.substring(i - 1, k);
    String b = str.substring(k, j);
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    builder.append(addBracket(a, i, k, sol[i][k], sol));
    builder.append(")(");
    builder.append(addBracket(b, 1, j - k, sol[k][j], sol));
    builder.append(")");
    return builder.toString();
  }

  String buildCombination(int N, int[][] sol) {
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i < N; i++) {
      builder.append((char) ('A' + (i - 1)));
    }
    return addBracket(builder.toString(), 1, N - 1, sol[1][N - 1], sol);
  }
}
