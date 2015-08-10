package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Sorting;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;

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
