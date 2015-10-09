package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.TortoiseAndHare;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;

import com.shuoma.annotation.Tag;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p/>
 * O(1) space, O(n) time and cannot modify the array (array is read only)
 */
@Tag(dss = Array, references = LeetCode, tricks = {TwoOrMorePointers, TortoiseAndHare})
public class FindtheDuplicateNumber {
  public int findDuplicate(int[] nums) {
    int fast, slow;

    fast = slow = nums[0];

    do {
      fast = nums[nums[fast]];
      slow = nums[slow];
    } while (fast != slow);

    slow = nums[0];
    while (fast != slow) {
      fast = nums[fast];
      slow = nums[slow];
    }

    return fast;
  }
}
