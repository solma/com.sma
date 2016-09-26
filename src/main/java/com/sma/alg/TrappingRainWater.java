package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.ForwardAndBackwardScan;

@Tag(dss = Array, references = LeetCode, tricks = ForwardAndBackwardScan)
public class TrappingRainWater {
  public static void main(String[] args) {
    //4,2,0,3,2,4,3,4
    //5,5,1,7,1,1,5,2,7,6
    //0,1,0,2,1,0,1,3,2,1,2,1
    System.out
        .println(new TrappingRainWater().trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
  }

  //first pass
  public int trap(int[] A) {
    if (A == null) { return -1; }
    int max = 0;

    //record idx of the max element
    for (int i = 0; i < A.length; i++) {
      if (A[i] > A[max]) { max = i; }
    }

    int water = 0;
    //forward scan
    for (int i = 0, top = 0; i < max; i++) {
      if (A[i] > top) { top = A[i]; }
      else { water += top - A[i]; }
    }
    //backward scan
    for (int i = A.length - 1, top = 0; i > max; i--) {
      if (A[i] > top) { top = A[i]; }
      else { water += top - A[i]; }
    }
    return water;
  }
}
