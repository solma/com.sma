package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dss = Array, references = LeetCode)
public class HouseRobber {

  public int rob(int[] num) {
    return rob(num, 0, num.length - 1);
  }

  public int rob(int[] num, int start, int end) {
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

  /** If houses form a circle. */
  public int robCircle(int[] num) {
    int n = num.length;
    if (n == 0) return 0;
    if (n == 1) return num[0];
    return Math.max(rob(num, 0, num.length - 2), rob(num, 1, num.length - 1));
  }

  public int robCircleStandardDp(int[] num) {
    int n = num.length;

    if (n == 0) return 0;
    if (n == 1) return num[0];

    // include i
    int[] maxIncludeCur = new int[n];
    boolean[] firstInIncludeCur = new boolean[n];
    firstInIncludeCur[0] = true;
    firstInIncludeCur[1] = false;

    int[] indexOfMax = new int[n];
    for (int i = 0; i < n; i++) {
      // include cur
      maxIncludeCur[i] = num[i];
      if (i >= 2) {
        if (i == n - 1 && firstInIncludeCur[indexOfMax[i - 2]]) {
          maxIncludeCur[i] += maxIncludeCur[indexOfMax[i - 2]] - num[0] - num[i] + Math.max(num[0], num[i]);
        } else {
          maxIncludeCur[i] += maxIncludeCur[indexOfMax[i - 2]];
          firstInIncludeCur[i] = firstInIncludeCur[indexOfMax[i - 2]];
        }
      }
      if (i > 0) {
        indexOfMax[i] = indexOfMax[i - 1];
        if (maxIncludeCur[i] > maxIncludeCur[indexOfMax[i - 1]]) {
          indexOfMax[i] = i;
        }
      }

    }
    return maxIncludeCur[indexOfMax[n - 1]];
  }

  public int robCircleSpaceEfficientDp(int[] num) {
    int n = num.length;
    switch (n) {
      case 0:
        return 0;
      case 1:
        return num[0];
      case 2:
        return Math.max(num[0], num[1]);
    }

    // initialize for i = 0
    int maxAtCurIfCurNotIn = 0;
    boolean isFirstInForMaxIfCurNotIn = false;

    int maxAtCur = num[0];
    boolean isFirstInForMax = true;
    for (int i = 1; i < n; i++) {
      int maxPrevIfPrevNotIn = maxAtCurIfCurNotIn;
      boolean isFirstInForMaxPrevNotIn = isFirstInForMaxIfCurNotIn;

      // update maxAtCurIfCurNotIn
      maxAtCurIfCurNotIn = maxAtCur;
      isFirstInForMaxIfCurNotIn = isFirstInForMax;

      // update maxAtCur considering include cur
      if (i == n - 1 && isFirstInForMaxPrevNotIn) {// special case
        maxAtCur = Math.max(maxAtCur, maxPrevIfPrevNotIn - num[0] + Math.max(num[0], num[n - 1]));
      } else {
        if (maxPrevIfPrevNotIn + num[i] >= maxAtCur) {
          maxAtCur = maxPrevIfPrevNotIn + num[i];
          isFirstInForMax = isFirstInForMaxPrevNotIn;
        }
      }
//      System.out.println(maxAtCurIfCurNotIn + " " + maxAtCur + " " + maxPrevIfPrevNotIn
//          + " " + isFirstInForMaxPrevNotIn + " " + isFirstInForMax);
    }
    return maxAtCur;
  }
}
