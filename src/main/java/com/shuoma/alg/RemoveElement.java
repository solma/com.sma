package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, references = LeetCode, tricks = InplaceSwap)
public class RemoveElement {
  public static void main(String[] args) {
    new RemoveElement().main();
  }

  public void main() {
    int[] A = new int[] {2, 3, 3};
    int len = removeElement(A, 3);
    for (int i = 0; i < len; i++)
      System.out.println(A[i]);
  }

  public int removeElement(int[] A, int elem) {
    int cnt = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] != elem)
        A[cnt++] = A[i];
    }
    return cnt;
  }
}
