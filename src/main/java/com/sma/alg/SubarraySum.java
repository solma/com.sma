package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Subarray;

import com.sma.annotation.Tag;

import java.util.Arrays;

/**
 * Given an array find an sub array with sum closest to K.
 */
@Tag(dss = Subarray)
public class SubarraySum {

  int[] a;
  int K;

  int sum;
  int diff;
  int[] cur;

  public SubarraySum(int[] a, int K) {
    this.a = a;
    this.K = K;
    cur = new int[2];
    diff = Integer.MAX_VALUE;
  }

  int[] findSum() {
    int n = a.length;
    if (n == 0) {
      return null;
    }

    int[] res = new int[3];
    int i;
    for (i = 0; i < a.length; i++) {
      sum += a[i];
      if (sum < K) {
        continue;
      }
      cur[1] = i;
      res = Arrays.copyOf(cur, 3);
      if (sum == K) {
        return res;
      }
      updateDiff(res);
    }
    updateDiff(res);
    return res;
  }

  int[] updateDiff(int[] res) {
    int diffWithoutLast;
    if (sum > K) {
      diff = Math.min(sum - K, diff);
      diffWithoutLast = K - (sum - a[cur[1]]);
      if (diffWithoutLast < diff) {
        diff = diffWithoutLast;
        res[1]--;
      }

      while (sum > K) {
        sum -= a[cur[0]];
        cur[0]++;
      }
    }

    if (K - sum < diff) {
      diff = K - sum;
      res[0] = cur[0];
      res[1] = cur[1];
    }
    diffWithoutLast = sum + (cur[0] > 0 ? a[cur[0] - 1] : 0) - K;
    if (diffWithoutLast > 0 && diffWithoutLast < diff) {
      diff = diffWithoutLast;
      res[0]--;
    }

    res[2] = diff;
    //System.out.println(Arrays.toString(res));
    return res;
  }
}
