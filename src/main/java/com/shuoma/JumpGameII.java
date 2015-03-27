package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Greedy, dl = D2, dss = Array, source = LeetCode)
public class JumpGameII {
  public static void main(String[] args) {
    //3,3,0,2,4,1,1,2,0,1
    JumpGameII ins = new JumpGameII();
    int[] arr = {3, 3, 0, 2, 4, 1, 1, 2, 0, 1};//{3,0,0,0,1};
    System.out.println(ins.jump(arr));
    System.out.println(ins.jumpDP(arr));
  }

  //Greedy
  public int jump(int[] A) {
    int n = A.length;
    if (n <= 1)
      return 0;
    int curIdx = 0;
    int jumps = 0;
    while (curIdx + A[curIdx] < n - 1) {
      int maxStep = 0;
      int nextIdx = 0;
      for (int step = 1; step <= A[curIdx]; step++) {
        if (A[curIdx + step] != 0 && step + A[curIdx + step] > maxStep) {
          nextIdx = curIdx + step;
          maxStep = step + A[curIdx + step];
        }
      }
      if (curIdx == nextIdx)
        return -1;
      curIdx = nextIdx;
      jumps++;
    }
    jumps++;
    return jumps;
  }

  //O(n^2) TLE
  public int jumpDP(int[] A) {
    int n = A.length;
    if (n < 1)
      return -1;
    int[] ret = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      if (i + A[i] >= n - 1) {
        if (i == n - 1)
          ret[i] = 0;
        else
          ret[i] = 1;
      } else {
        ret[i] = Integer.MAX_VALUE;
        for (int j = A[i]; j >= 1; j--)
          if (ret[i + j] != Integer.MAX_VALUE && ret[i] > ret[i + j] + 1)
            ret[i] = ret[i + j] + 1;
      }
      System.out.println(ret[i]);
    }
    return ret[0];
  }
}
