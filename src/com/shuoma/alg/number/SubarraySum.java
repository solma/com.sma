package com.shuoma.alg.number;

import java.util.Arrays;

/**
 * Given an array find an sub array with sum closest to K.
 */
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
    if (sum < K && K - sum < diff) {
      cur[1] = i - 1;
      res = Arrays.copyOf(cur, 3);
      res[2] = K - sum;
    }
    return res;
  }

  void updateDiff(int[] res) {
    diff = Math.min(Math.abs(sum - K), diff);

    int removeFirst = Math.abs(sum - a[cur[0]] - K), removeLast = Math.abs(sum - a[cur[1]] - K);
    if (removeFirst < diff && removeFirst <= removeLast) {
      diff = removeFirst;
      res[0]++;
    } else {
      if (removeLast < diff && removeLast < removeFirst) {
        diff = removeLast;
        res[1]--;
      }
    }
    res[2] = diff;

    while (sum > K) {
      sum -= a[cur[0]];
      cur[0]++;
    }
    //System.out.println(Arrays.toString(res));
  }
}
