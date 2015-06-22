package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, references = LeetCode)
public class MergeTwoArrays {
  public void merge(int A[], int m, int B[], int n) {
    int i = 0, j = 0, k = 0;
    int[] C = new int[m + n];

    while (i < m && j < n) {
      if (A[i] < B[j]) {
        C[k] = A[i];
        i++;
      } else {
        C[k] = B[j];
        j++;
      }
      k++;
    }
    int x;
    if (i == m) {  //A reaches the end
      for (x = 0; x < k; x++)
        A[x] = C[x];
      for (x = j; x < n; x++)
        A[x + m] = B[x];
    } else {
      for (x = i; x < m; x++)
        C[x + n] = A[x];
      for (x = 0; x < m + n; x++)
        A[x] = C[x];
    }
  }
}
