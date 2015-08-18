package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BinarySearch, dss = Array, references = LeetCode)
public class SearchInsertionPosition {

  // first index i such that A[i] >= target
  public int searchInsert(int[] A, int target) {
    int n = A.length;
    int m, l = -1, r = n;
    while (l + 1 != r) {
      m = l + (r - l) / 2;
      if (A[m] < target) { l = m; }
      else { r = m; }
    }
    return r;
  }
}
