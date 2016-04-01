package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.HashSet;
import java.util.Set;

@Tag(dl = D2, dss = Hash, references = LeetCode) public class LongestConsecutiveSequence {
  public static void main(String[] args) {
    new LongestConsecutiveSequence().main();
  }

  public void main() {
    int[] num = {0};
    longestConsecutive(num);
  }

  public int longestConsecutive(int[] num) {
    Set<Integer> nums = new HashSet<>();
    int i;
    for (i = 0; i < num.length; i++) { nums.add(num[i]); }
    int maxLength = 0;
    for (i = 0; i < num.length; i++) {
      if (!nums.contains(num[i])) continue;
      maxLength = Math.max(maxLength,
          consecutiveSequence(nums, num[i], 1)
              + consecutiveSequence(nums, num[i], -1) + 1);
      nums.remove(num[i]);
    }
    return maxLength;
  }

  int consecutiveSequence(Set<Integer> nums, int val, int increment) {
    int ret = 0;
    int num = val + increment;
    while (nums.contains(num)) {
      nums.remove(num);
      ret += 1;
      num += increment;
    }
    return ret;
  }
}
