package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;

import com.shuoma.annotation.Tag;
import com.shuoma.util.RandomUtil;

import java.util.Arrays;

/*
 * reference: http://www.mitbbs.com/article_t/JobHunting/32719993.html
 *
 * Given an array consisting of 0's and 1's. Reverse at most one element (0->1 or 1->0) to maximize
 * the length of a homogeneous subarray (a homogeneous array is an array of all 0's or all 1's).
 * Return the length. e.g.
 * given [1 0 1]，reverse 0，return 3
 * given [1 1 0 1 0 0]，reverse the 0 or 1 in the middle，return 4.
 */
@Tag(algs = DynamicProgramming, dss = Subarray)
public class LongestContinuousSubarrayWithChange {

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      // 0, 0, 0, 1, 1, 0, 0; 1, 1, 0, 1, 0, 0, 0
      // int[] ary = new int[] {1, 1, 0, 1, 0, 0, 0};
      int[] ary = RandomUtil.genRandomArray(7, 2, false, false);
      int[] res = new int[3];
      res[0] = maxConsecutive(ary);
      res[1] = maxConsecutiveDp(ary);
      res[2] = maxConsecutiveBase(ary);
      if (res[0] != res[2] || res[1] != res[2]) {
        System.out.println(Arrays.toString(ary));
        System.out.println(Arrays.toString(res));
        System.out.println();
      }
    }
  }

  /**
   * try to reverse 1 and 0 respectively
   * use two variables when reverse for either case
   */
  static int maxConsecutive(int[] ary) {
    int max = getLongestHomogenousSubarray(ary); // no change
    for (int num : new int[]{0, 1}) {
      int replaced = -1, begin = 0;
      for (int cur = 0; cur < ary.length; cur++) {
        if (ary[cur] == num) {
          if (replaced != -1) {
            max = Math.max(max, cur - begin);
            begin = replaced + 1;
          }
          replaced = cur; // the first 0/1
        }
      }
      max = Math.max(max, ary.length - begin);
      //System.out.println(max);
    }
    return max;
  }

  /**
   * dynamic programming: complex logic but correct solution
   */
  static int maxConsecutiveDp(int[] ary) {
    int[] reverse = new int[ary.length];
    int[] noReverse = new int[ary.length];
    // when noReverse gets the max, records which element is reversed
    int[] reversedElementForNoReverse = new int[ary.length];
    reverse[0] = noReverse[0] = 1;
    reversedElementForNoReverse[0] = -1;

    int maxL = 1;
    for (int i = 1; i < ary.length; i++) {
      // do not change ary[i]
      if (ary[i] != ary[i - 1]) {
        noReverse[i] = reverse[i - 1] + 1;
        reversedElementForNoReverse[i] = i - 1;
      } else {
        noReverse[i] = noReverse[i - 1] + 1;
        reversedElementForNoReverse[i] = reversedElementForNoReverse[i - 1];
      }

      // change ary[i]
      if (ary[i] != ary[i - 1]) {
        if (reversedElementForNoReverse[i - 1] < 0) // not reverse any yet before
          reverse[i] = noReverse[i - 1] + 1;
        else
          reverse[i] = i - reversedElementForNoReverse[i - 1];
      } else {
        reverse[i] = 1;
      }

      maxL = Math.max(maxL, Math.max(reverse[i], noReverse[i]));
    }
    // System.out.println(Arrays.toString(reverse));
    // System.out.println(Arrays.toString(noReverse));
    // System.out.println(Arrays.toString(reversedElementForNoReverse));

    return maxL;
  }

  static int maxConsecutiveBase(int[] ary) {
    int n = ary.length;
    int[] cpy;
    int maxL = 1;
    for (int i = 0; i < n; i++) {
      cpy = Arrays.copyOf(ary, n);
      cpy[i] = 1 - cpy[i];
      maxL = Math.max(maxL, getLongestHomogenousSubarray(cpy));
    }
    return Math.max(maxL, getLongestHomogenousSubarray(ary));
  }

  private static int getLongestHomogenousSubarray(int[] cpy) {
    int maxL = 1, curL = maxL;
    for (int i = 1; i < cpy.length; i++) {
      if (cpy[i] == cpy[i - 1])
        curL++;
      else {
        maxL = Math.max(maxL, curL);
        curL = 1;
      }
    }
    maxL = Math.max(maxL, curL);
    return maxL;
  }
}
