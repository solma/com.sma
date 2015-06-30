package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(algs = DynamicProgramming, dss = Array, references = LeetCode)
public class HouseRobber {

  public int rob(int[] num) {
    return rob(num, 0, num.length - 1);
  }

  int rob(int[] num, int start, int end) {
    int n = end - start + 1;
    if (n == 0) {
      return 0;
    }
    int notRobCurMax = 0;
    int ret = num[start];
    for (int i = start + 1; i <= end; i++) {
      int tmp = ret;
      ret = Math.max(ret, notRobCurMax + num[i]);
      notRobCurMax = tmp;
    }
    return ret;
  }

  int rob1(int[] num, int start, int end) {
    int n = end - start + 1;
    if (n <= 0) {
      return 0;
    }
    int ret = 0;
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
    int[][] decisionR1 = new int[n][3];
    int[][] decisionR2 = new int[n][3];
    for (int i = start; i <= end; i++) {
      if (i == end && i > 0 && decisionR2[i - 1][2] == 1) {
        decisionR1[i][1] = rob1(num, 1, i - 2) + num[i];
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
      System.out.println(num[i] + " " + Arrays.toString(decisionR1[i]) + " " + Arrays.toString(decisionR2[i]));
      ret = Math.max(ret, decisionR1[i][0]);
    }
    return ret;
  }

  /** If houses form a circle. */
  public int robCircle(int[] num) {
    int n = num.length;
    if (n == 0) return 0;
    if (n == 1) return num[0];
    return Math.max(rob1(num, 0, num.length - 2), rob1(num, 1, num.length - 1));
  }
}
