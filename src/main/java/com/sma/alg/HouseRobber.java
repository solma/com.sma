package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Complexity.Linear;

@Tag(algs = DynamicProgramming, dss = Array, timecomplexity = Linear, references = LeetCode)
public class HouseRobber {

  public int rob(int[] num) {
    return rob1(num, 0, num.length - 1);
  }

  int rob1(int[] num, int start, int end) {
    int n = end - start + 1;
    if (n == 0) { return 0; }
    int notRobCurMax = 0;
    int ret = num[start];
    for (int i = start + 1; i <= end; i++) {
      int tmp = ret;
      ret = Math.max(ret, Math.max(notRobCurMax, 0) + num[i]);
      notRobCurMax = tmp;
    }
    return ret;
  }

  int rob2(int[] num, int start, int end) {
    int n = end - start + 1;
    if (n <= 0) { return 0; }
    int[] dp = new int[n];
    dp[0] = num[start];
    if (n == 1) { return dp[0]; }
    dp[1] = Math.max(num[start + 1], dp[0]);
    for (int j = start + 2; j <= end; j++) {
      int i = j - start;
      dp[i] = Math.max(dp[i - 1], num[j] + Math.max(0, dp[i - 2]));
    }
    return dp[n - 1];
  }

  int rob2Bug1(int[] num, int start, int end) {
    int n = end - start + 1;
    if (n <= 0) { return 0; }
    int[] dp = new int[n];
    dp[0] = num[start];
    if (n == 1) { return dp[0]; }
    dp[1] = Math.max(num[start + 1], dp[0]);
    for (int j = start + 2; j <= end; j++) {
      int i = j - start;
      if (dp[i - 1] < 0 && dp[i - 2] < 0 && num[i] > 0) { dp[i] = num[i]; }
      else { dp[i] = Math.max(dp[i - 1], num[j] + dp[i - 2]); }
    }
    return dp[n - 1];
  }

  int rob3(int[] num, int start, int end) {
    int n = end - start + 1;
    if (n <= 0) {
      return 0;
    }
    int ret = Integer.MIN_VALUE;
    int[][] decisionR1 = new int[n][3];
    for (int j = start; j <= end; j++) {
      int i = j - start;
      decisionR1[i][1] = (i > 0 ? decisionR1[i - 1][2] : 0) + num[j];
      decisionR1[i][2] = i > 0 ? decisionR1[i - 1][0] : 0;
      decisionR1[i][0] = Math.max(decisionR1[i][1], decisionR1[i][2]);
      ret = Math.max(ret, decisionR1[i][0]);
    }
    return ret;
  }

  int rob1Circle(int[] num, int start, int end) {
    int n = end - start + 1;
    if (n == 0) {
      return 0;
    }
    int ret = 0;
    int[][] decisionR1 = new int[n][3]; // value
    int[][] decisionR2 = new int[n][3]; // flag for including the first house
    for (int i = start; i <= end; i++) {
      if (i == end && i > 0 && decisionR2[i - 1][2] == 1) {
        decisionR1[i][1] = rob3(num, 1, i - 2) + num[i];
        decisionR2[i][1] = -1;

      } else {
        decisionR1[i][1] = (i > 0 ? decisionR1[i - 1][2] : 0) + num[i];
        decisionR2[i][1] = i > 0 ? decisionR2[i - 1][2] : 1;
      }
      decisionR1[i][2] = i > 0 ? decisionR1[i - 1][0] : 0;
      decisionR2[i][2] = i > 0 ? decisionR2[i - 1][0] : -1;

      if (decisionR1[i][1] != decisionR1[i][2]) {
        if (decisionR1[i][1] > decisionR1[i][2]) {
          decisionR1[i][0] = decisionR1[i][1];
          decisionR2[i][0] = decisionR2[i][1];
        } else {
          decisionR1[i][0] = decisionR1[i][2];
          decisionR2[i][0] = decisionR2[i][2];
        }
      } else { // R2 could be either true or false
        decisionR1[i][0] = decisionR1[i][1]; // or [i][2]
        decisionR2[i][0] = 0;
      }
      //System.out.println(num[i] + " " + Arrays.toString(decisionR1[i]) + " " + Arrays.toString(decisionR2[i]));
      ret = Math.max(ret, decisionR1[i][0]);
    }
    return ret;
  }

  /** If houses form a circle. */
  public int robCircle(int[] num) {
    int n = num.length;
    if (n == 0) return 0;
    if (n == 1) return num[0];
    return Math.max(rob2(num, 0, num.length - 2), rob2(num, 1, num.length - 1));
  }
}
