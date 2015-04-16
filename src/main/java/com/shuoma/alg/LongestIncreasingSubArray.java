package com.shuoma.alg;


import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;

import com.shuoma.annotation.Tag;

/** Find longest increasing subarray for specified array. */
@Tag(algs = DynamicProgramming, dss = Subarray)
public class LongestIncreasingSubArray {

  int longestIncreasingSubarray(final int[] a) {
    int n = a.length;
    if (n == 0) return 0;
    int max = 1, cur = 1;
    for (int i = 1; i < n; i++) {
      if (a[i] > a[i - 1]) {
        cur++;
      } else {
        max = Math.max(max, cur);
        cur = 1;
      }
    }
    return max;
  }
}
