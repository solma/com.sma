package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.BinarySearch;

import com.shuoma.annotation.Tag;

@Tag(algs = BinarySearch)
public class SearchInRotatedArray {

  // works for with an w/o duplicates
  // without find the pivot
  int search(int[] A, int key) {
    int N = A.length;
    int L = 0;
    int R = N - 1;

    while (L <= R) {
      int M = L + ((R - L) / 2);
      if (A[M] == key)
        return M;

      if (A[L] < A[M]) {
        // the left half is sorted
        if (A[L] <= key && key < A[M])
          R = M - 1;
        else
          L = M + 1;
      }
      else {
        if (A[L] > A[M]) {
          // the right half is sorted
          if (A[M] < key && key <= A[R])
            L = M + 1;
          else
            R = M - 1;
        } else {//A[L]==A[M]
          L++;
        }
      }
    }
    return -1;
  }

  //find the pivot
  int findPivot(int[] A) {
    int l = 0, r = A.length - 1, m;
    while (A[l] > A[r]) {
      m = l + ((r - l) >> 1);
      if (A[m] > A[r]) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    return l;
  }
}
