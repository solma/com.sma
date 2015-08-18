package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, references = LeetCode, tricks = InplaceSwap)
public class RemoveDuplicatesfromSortedArray {

  public int removeDuplicates(int[] A) {
    if (A.length < 1) { return 0; }
    int cnt = 1;
    for (int i = 1; i < A.length; i++) {
      if (A[i] > A[i - 1])
        A[cnt++] = A[i];
    }
    return cnt;
  }
}
