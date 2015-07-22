package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;
import com.shuoma.util.RandomUtil;

/**
 * Given an array find index triplet <i, j, k> such that i < j < k and a[i] <= a[j] <= a[k]
 */
@Tag(algs = DynamicProgramming, dss = MonotonicSequence, references = Interview)
public class IncreasingTriplet {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      int[] a = RandomUtil.genRandomArray(10, 20, false, false);
      int[] idx = increasingTriplet(a);
      if (idx != null) {
        //System.out.println(Arrays.toString(a));
        assert (a[idx[0]] < a[idx[1]] && a[idx[1]] < a[idx[2]]);
        //System.out.println(a[idx[0]] + " " + a[idx[1]] + " " + a[idx[2]]);
      }
    }
  }

  static int[] increasingTriplet(int[] a) {
    int smallIdx = 0, middleIdx = -1, minIdx = -1;
    for (int i = 1; i < a.length; i++) {
      int cur = a[i];
      if (cur < a[smallIdx]) {
        if (middleIdx == -1) {
          smallIdx = i;
        } else if (minIdx == -1 || cur < a[minIdx]) {
          minIdx = i;
        } else if(minIdx >= 0) {
          smallIdx = minIdx;
          middleIdx = i;
          minIdx = -1;
        }
      } else if ((middleIdx == -1  && cur > a[smallIdx]) || (middleIdx >=0 && cur < a[middleIdx])) {
        middleIdx = i;
      } else if (middleIdx >=0 && cur > a[middleIdx]) {
        return new int[]{smallIdx, middleIdx, i};
      }
    }
    return null;
  }
}
