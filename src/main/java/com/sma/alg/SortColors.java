package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Sorting;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.InplaceSwap;

import com.sma.annotation.Tag;
import com.sma.util.ArrayUtil;

@Tag(algs = Sorting, dss = Array, references = LeetCode, tricks = InplaceSwap)
public class SortColors {
  public static void main(String[] args) {
    new SortColors().sortColors(new int[] {2});
  }

  public void sortColors(int[] A) {
    int l = 0, r = A.length - 1;
    for (int i = 0; i <= r; ) {
      if (A[i] == 0)
        ArrayUtil.swap(A, i++, l++);
      else {
        if (A[i] == 2)
          ArrayUtil.swap(A, i, r--);
        else
          i++;
      }
    }
  }
}
