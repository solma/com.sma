package com.shuoma.alg;
import java.util.*;

public class TwoSum {
  public static void main(String[] args) {
    new TwoSum().twoSum(new int[] {5, 75, 25}, 100);
  }

  //second pass
  public int[] twoSum(int[] numbers, int target) {
    int n = numbers.length;
    if (n == 0)
      return null;
    ArrayList<NumberWithIndex> nums = new ArrayList<>();
    for (int i = 0; i < n; i++)
      nums.add(new NumberWithIndex(numbers[i], i + 1));
    Collections.sort(nums);
    int[] ret = new int[2];
    int l = 0, r = n - 1;
    while (l < r) {
      if (nums.get(l).val > target && target > 0)
        break;
      if (nums.get(l).val + nums.get(r).val > target)
        r--;
      else {
        if (nums.get(l).val + nums.get(r).val < target)
          l++;
        else {
          ret[0] = nums.get(l).idx;
          ret[1] = nums.get(r).idx;
          if (ret[0] > ret[1]) {
            int tmp = ret[1];
            ret[1] = ret[0];
            ret[0] = tmp;
          }
          return ret;
        }
      }
    }
    return null;
  }

  class NumberWithIndex implements Comparable<NumberWithIndex> {
    int val;
    int idx;

    public NumberWithIndex(int val, int idx) {
      this.val = val;
      this.idx = idx;
    }

    public int compareTo(NumberWithIndex other) {
      return val - other.val;
    }
  }



  //first pass
  public int[] twoSumFirstPass(int[] numbers, int target) {
    // Start typing your Java solution below
    // DO NOT write main() function
    Element[] nums = new Element[numbers.length];
    for (int i = 0; i < numbers.length; i++)
      nums[i] = new Element(numbers[i], i);
    Arrays.sort(nums);
    int low = 0, high = numbers.length - 1;
    int diff;

    while (low < high) {
      diff = nums[low].key + nums[high].key - target;
      if (diff == 0) {
        System.out.println(nums[low].pos + 1);
        System.out.println(nums[high].pos + 1);
        if (nums[low].pos < nums[high].pos)
          return new int[] {nums[low].pos + 1, nums[high].pos + 1};
        else
          return new int[] {nums[high].pos + 1, nums[low].pos + 1};
      } else {
        if (diff > 0)
          high -= 1;
        else
          low += 1;
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

