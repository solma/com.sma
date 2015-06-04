package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.SlidingWindow;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(algs = SlidingWindow, dss = {Array, HashTable}, reference = LeetCode)
public class ContainsDuplicate {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> lastIndex = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (lastIndex.containsKey(nums[i]) && lastIndex.get(nums[i]) + k >= i) {
        return true;
      }
      lastIndex.put(nums[i], i);
    }
    return false;
  }

  public boolean containsDuplicate(int[] nums) {
    return containsNearbyDuplicate(nums, nums.length - 1);
  }

  /**
   * Given an array of integers, find out whether there are two distinct indices i and j in the
   * array such that the difference between nums[i] and nums[j] is at most t and the difference
   * between i and j is at most k.
   */
  // https://leetcode.com/discuss/38206/ac-solution-in-java-using-o-n-bucket-with-explanation
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (k < 1 || t < 0) return false;
    if (t == 0) return containsNearbyDuplicate(nums, k);

    Map<Long, Long> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
      long bucket = remappedNum / t;
      if (map.containsKey(bucket)
          || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
          || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
        return true;
      if (map.entrySet().size() >= k) {
        long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / t;
        map.remove(lastBucket);
      }
      map.put(bucket, remappedNum);
    }
    return false;
  }
}
