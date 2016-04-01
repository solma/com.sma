package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.SlidingWindow;
import static com.sma.annotation.Tag.DataStructure.Subarray;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = SlidingWindow, dss = Subarray, references = LeetCode)
public class MinimumSizeSubarraySum {

  public int minSubArrayLen(int s, int[] nums) {
    int ans = Integer.MAX_VALUE;
    int sIdx = 0, eIdx = 0, sum = 0;

    while (eIdx < nums.length) {
      while (sum < s && eIdx < nums.length) {
        sum += nums[eIdx++];
      }
      while (sum >= s) {
        ans = Math.min(ans, eIdx - sIdx);
        sum -= nums[sIdx++];
      }
    }

    return (ans == Integer.MAX_VALUE ? 0 : ans);
  }
}
