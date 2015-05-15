package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Subarray, source = LeetCode)
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
