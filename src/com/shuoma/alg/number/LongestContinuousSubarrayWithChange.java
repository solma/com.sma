package com.shuoma.alg.number;

import com.shuoma.util.RandomUtil;

import java.util.Arrays;

/*
 * reference: http://www.mitbbs.com/article_t/JobHunting/32719993.html
 *
 * an array consisting of 0's and 1's. Reverse at most one element (0->1 or 1->0) to maximize
 * the length of a homogeneous subarray (a homogeneous array is an array of all 0's or all 1's).
 * Return the length.
 * e.g. [1 0 1]，reverse 0，return 3
 * [1 1 0 1 0 0]，reverse the 0 or 1 in the middle，return 4.
 */

public class LongestContinuousSubarrayWithChange {

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      // new int[] {1, 1, 0, 1, 0, 0};
      int[] ary = RandomUtil.genRandomArray(7, 1, false, false);
      int[] res = new int[3];
      res[0] = maxConsecutive(ary);
      res[1] = maxConsecutive1(ary);
      if (res[0] != res[1]) {
        System.out.println(Arrays.toString(ary));
        System.out.println(Arrays.toString(res));
        System.out.println();
      }
    }
  }

  static int maxConsecutive(int[] ary) {
    int[] changed = {0, 0};
    int[] noChange = {0, 0};
    int maxL = 0;

    for (int x : ary) {
      maxL = Math.max(maxL, Math.max(++changed[x], ++noChange[x]));
      changed[1 - x] = 1 + noChange[1 - x];
      noChange[1 - x] = 0;
      //System.out.println(maxL + " " + Arrays.toString(changed) + " " + Arrays.toString(noChange));
    }

    return maxL;
  }

  static int maxConsecutive1(int[] ary) {
    int[] changed = new int[ary.length];
    int[] noChange = new int[ary.length];
    // when noChange reaches max record if any element before i has been reversed
    boolean[] hasChangedBefore = new boolean[ary.length];
    changed[0] = noChange[0] = 1;
    int maxL = 1;

    for (int i = 1; i< ary.length; i++) {
      if (ary[i] == ary[i - 1]) {
        noChange[i] = noChange[i - 1] + 1;
      } else {
        noChange[i] = changed[i - 1] + 1;
        hasChangedBefore[i] = true;
        if(!hasChangedBefore[i - 1])
          changed[i] = noChange[i - 1] + 1;
      }
      maxL = Math.max(maxL, Math.max(changed[i], noChange[i]));
    }

    return maxL;
  }
}
