package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Sorting;
import static com.sma.annotation.Tag.DataStructure.Array;

import com.sma.annotation.Tag;

/**
 * Given an array, put all odd numbers before even numbers and keep the relative
 * order among odd numbers and even numbers unchanged.
 */
@Tag(algs = Sorting, dss = Array)
public class ParitySorting {

  void paritySorting(int[] arr) {
    int n = arr.length;
    if (n < 2) {
      return;
    }

    for (int i = 0, j = 0; i < n; i++) {
      if ((arr[i] & 1) > 0) {
        int tmp = arr[i];
        for (int k = i; k > j; k--) {
          arr[k] = arr[k - 1];
        }
        arr[j] = tmp;
        j++;
      }
    }
  }
}
