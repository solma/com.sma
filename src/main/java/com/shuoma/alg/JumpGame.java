package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Greedy, dss = Array, reference = LeetCode)
public class JumpGame {

  public boolean canJump(int[] A) {
    int n = A.length;
    if (n <= 1)
      return true;
    int i = 0;
    while (i + A[i] < n - 1) {
      int m = 0;
      int mj = 0;
      for (int j = 1; j <= A[i]; j++) {
        if (A[i + j] != 0 && j + A[i + j] > m) {
          mj = j;
          m = j + A[i + j];
        }
      }
      if (mj == 0)
        return false;
      i += mj;
    }
    return true;
  }
}
