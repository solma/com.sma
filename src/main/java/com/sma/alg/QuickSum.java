package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Complexity.Polynomial;

/**
 * Given a string, insert minimum number of '+' so the partitions sum up to the
 * specific number. */
@Tag(algs = DynamicProgramming, timecomplexity = Polynomial, dss = Array)
public class QuickSum {
  //TODO: unfinished
  String insert(String s, int m) {
    int n = s.length();
    int[][][] cntMem = new int[n][n][m];
    int[][][] insertMem = new int[n][n][m];
    int[][][] partSumMem = new int[n][n][m];
    for (int len = 1; len <= n; len++) {
      for (int i = 0; i <= n - len; i++) {
        int j = i + len - 1;
        for (int k = i; k <= j; k++) {
          for (int sum = 1; sum <= Math.min(m, parse(s, i, j)); sum++) {
            for (int partSum = 1; partSum <= Math.min(sum, Math.min(parse(s, i, k), sum - parse(s, k + 1, j))); partSum++) {
              //int tmp.txt =
              cntMem[i][j][sum] = cntMem[i][k][partSum] + cntMem[k + 1][j][sum - partSum] + 1;
              insertMem[i][j][sum] = k; //after k
              partSumMem[i][j][sum] = partSum;
            }
          }
        }
      }
    }
    return buildString(insertMem, partSumMem, s, m, 0, s.length() - 1);
  }

  int parse(String s, int i, int j) {
    String sub = s.substring(i, j + 1);
    return sub == null ? 0 : Integer.valueOf(sub);
  }

  String buildString(int[][][] insertMem, int[][][] partSumMem, String s, int sum, int start, int end) {
    int k = insertMem[start][end][sum];
    int partSum = partSumMem[start][end][sum];
    return buildString(insertMem, partSumMem, s.substring(start, k + 1), partSum, start, k)
        + '+'
        + buildString(insertMem, partSumMem, s.substring(k + 1, end + 1), sum - partSum, k + 1, end);
  }
}
