package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BinarySearch, dss = Array, source = LeetCode)
public class SearchInsertionPosition {
  //second pass
  public int searchInsert(int[] A, int target) {
    int n = A.length;
    int m, l = -1, r = n;
    while (l + 1 != r) {
      m = l + (r - l) / 2;
      if (A[m] < target)
        l = m;
      else
        r = m;
    }
    return r;
  }

  //first pass
  public int searchInsertFirstPass(int[] A, int target) {
    int l = -1, r = A.length;
    while (l + 1 != r) {
      int m = l + (r - l) / 2;
      if (A[m] == target)
        return m;
      else {
        if (A[m] > target)
          r = m;
        else
          l = m;
      }
    }
    return r;
  }
}
