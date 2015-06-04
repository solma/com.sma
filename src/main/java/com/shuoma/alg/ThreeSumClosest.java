package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.FromTwoEndsToMiddle;

import com.shuoma.annotation.Tag;

import java.util.*;

@Tag(dss = Array, reference = LeetCode, tricks = FromTwoEndsToMiddle)
public class ThreeSumClosest {
  public static void main(String[] args) {
    ThreeSumClosest ins = new ThreeSumClosest();
    for (ArrayList<Integer> triplet : ins
        .threeSumClosest(new int[] {-25, -10, -7, -3, 2, 2, 8, 10, 11}, 13))
      System.out.println(triplet);
  }

  //O(n^2)
  public List<ArrayList<Integer>> threeSumClosest(int[] num, int target) {
    List<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

    if (num.length < 3) { // if less than three items then return 0
      return ret;
    }

    Arrays.sort(num);
    int closetSum = num[0] + num[1] + num[2];
    for (int i = 0; i < num.length; ++i) {
      if (i > 0 && num[i] == num[i - 1])
        continue;
      int l = i + 1, r = num.length - 1;

      //O(n)
      while (l < r) {
        int sum = num[i] + num[l] + num[r];
        if (Math.abs(sum - target) <= Math.abs(closetSum - target)) {
          if (Math.abs(sum - target) < Math.abs(closetSum - target)) {
            ret.clear();
            closetSum = sum;
          }
          ArrayList<Integer> triplets = new ArrayList<Integer>();
          Collections.addAll(triplets, num[i], num[l], num[r]);
          ret.add(triplets);
        }
        //                if (sum == target) {
        //                    return closetSum;
        //                } else
        if (sum <= target) {
          l++;
        } else {
          r--;
        }
      }//end-while
    }
    return ret;
  }

  //O(n^2logn)
  public int threeSumClosestFirstTry(int[] num, int target) {
    if (num.length < 3) { // if less than three items then return 0
      return Integer.MAX_VALUE;
    }
    Arrays.sort(num);
    int res = num[0] + num[1] + num[2];
    for (int i = 0; i < num.length - 2; ++i) {
      if (i > 0 && num[i] == num[i - 1])
        continue;
      for (int j = i + 1; j < num.length - 1; ++j) {
        if (j > i + 1 && num[j] == num[j - 1])
          continue;

        // binary search for the third
        int start = j + 1, end = num.length - 1;
        while (start <= end) {
          int mid = (start + end) / 2;
          int curRes = num[i] + num[j] + num[mid];
          int diff = curRes - target;
          if (Math.abs(diff) < Math.abs(res - target)) {
            res = curRes;
          }
          if (diff < 0) {
            start = mid + 1;
          } else {
            end = mid - 1;
          }
        }
      }
    }
    return res;
  }
}
