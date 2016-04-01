package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.BinarySearch;
import static com.sma.annotation.Tag.DataStructure.Subarray;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = BinarySearch, dl = D3, dss = Subarray, references = LeetCode)
public class FindPeakElement {
  // adjacent elements are different
  public int findPeakElement(int[] a) {
    int n = a.length;
    if (n == 0) { return -1; }
    int l = 0, r = n - 1;
    while (l <= r) {
      int m = l + ((r - l) >> 1);
      if (((m > 0 && a[m] > a[m - 1]) || m == 0)
          && ((m < n - 1 && a[m] > a[m + 1]) || m == n - 1)) {
        return m;
      } else {
        if (m > 0 && a[m] <= a[m - 1]) { r = m - 1; }
        else { l = m + 1; }
      }
    }
    return -1;
  }
}
