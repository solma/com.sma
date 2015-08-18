package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

/**
 * Find common elements in N sorted arrays with no extra space
 */
@Tag(algs = Greedy, dss = {Array, MonotonicSequence}, references = Interview)
public class CommonElementOfSortedArrays {
  public static void main(String[] args) {
    int[][] a = {
        {10, 160, 200, 500, 500,},
        {4, 150, 160, 170, 500,},
        {2, 160, 200, 202, 203,},
        {3, 150, 155, 160, 300},
        {3, 150, 155, 160, 301}
    };

    new CommonElementOfSortedArrays().commonElementOfSortedArrays(a);
  }

  void commonElementOfSortedArrays(int[][] sortedArrays) {
    int n = sortedArrays.length;

    // find the array with the largest 1st element
    int ref = 0;
    int max = sortedArrays[0][0];
    for (int i = 1; i < n; i++) {
      if (sortedArrays[i][0] > max) {
        max = sortedArrays[i][0];
        ref = i;
      }
    }

    // initialize the first element of rest arrays to be pointers
    for (int i = 0; i < n; i++) {
      if (i != ref) {
        sortedArrays[i][0] = 0;
      }
    }

    for (int i = 0; i < n; i++) {
      int val = sortedArrays[ref][i];
      boolean print = true;
      for (int j = 1; j < n; j++) {
        while (sortedArrays[j][sortedArrays[j][0]] < val && sortedArrays[j][0] < n - 1) sortedArrays[j][0]++;
        if (sortedArrays[j][sortedArrays[j][0]] != val) print = false;
      }
      if (print) System.out.println(val);
    }
  }
}
