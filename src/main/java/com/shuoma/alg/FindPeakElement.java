package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = BinarySearch, dl = D3, dss = Subarray, reference = LeetCode)
public class FindPeakElement {
  public int findPeakElement(int[] a) {
    int n = a.length;
    if (n == 0)
      return -1;
    int l = 0, r = n - 1, m;
    while (l <= r) {
      m = l + ((r - l) >> 1);
      if (((m > 0 && a[m] > a[m - 1]) || m == 0) && ((m < n - 1 && a[m] > a[m + 1])
          || m == n - 1)) {
        return m;
      } else {
        if (m > 0 && a[m] <= a[m - 1])
          r = m - 1;
        else
          l = m + 1;
      }
    }
    return -1;
  }
}
