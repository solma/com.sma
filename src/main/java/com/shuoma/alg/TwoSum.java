package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.FromTwoEndsToMiddle;
import static com.shuoma.annotation.Tag.Trick.TwoOrMorePointers;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dss = Array, references = LeetCode, tricks = {FromTwoEndsToMiddle, TwoOrMorePointers})
public class TwoSum {
  public static void main(String[] args) {
    new TwoSum().twoSum(new int[] {5, 75, 25}, 100);
  }

  public int[] twoSum(int[] numbers, int target) {
    Element[] nums = new Element[numbers.length];
    for (int i = 0; i < numbers.length; i++)
      nums[i] = new Element(numbers[i], i);
    Arrays.sort(nums);
    int low = 0, high = numbers.length - 1;

    while (low < high) {
      int diff = nums[low].key + nums[high].key - target;
      if (diff == 0) {
        if (nums[low].pos < nums[high].pos)
          return new int[] {nums[low].pos + 1, nums[high].pos + 1};
        else
          return new int[] {nums[high].pos + 1, nums[low].pos + 1};
      } else {
        if (diff > 0) { high -= 1; }
        else { low += 1; }
      }
    }
    return null;
  }

  class Element implements Comparable {
    int key;
    int pos;

    public Element(int key, int pos) {
      this.key = key;
      this.pos = pos;
    }

    public int compareTo(Object other) {
      return key - ((Element) (other)).key;
    }
  }
}

