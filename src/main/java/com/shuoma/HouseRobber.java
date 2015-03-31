package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dss = Array, source = LeetCode)
public class HouseRobber {

  public int rob(int[] num) {
    int n = num.length;
    if (n == 0)
      return 0;
    int notRobCurMax = 0;
    int ret = num[0];
    for (int i = 1; i < n; i++) {
      int tmp = ret;
      ret = Math.max(ret, notRobCurMax + num[i]);
      notRobCurMax = tmp;
    }
    return ret;
  }
}
