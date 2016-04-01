package com.sma.alg;

// reference: weixin daizhiguizhong
// B[i]=A[0]*A[1]*...A[i-1]*A[i+1]*...A[n]

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.ForwardAndBackwardScan;

import com.sma.annotation.Tag;

@Tag(dss = Array, tricks = ForwardAndBackwardScan, references = LeetCode)
public class ProductOfArrayExceptSelf {

  public int[] transform(int[] nums) {
    int n = nums.length;
    if (n < 2)
      return nums;

    // forward[i] = π(0...i)A[i]
    int[] forward = new int[n];
    forward[0] = nums[0];
    for (int i = 1; i < n; i++) {
      forward[i] = forward[i - 1] * nums[i];
    }

    // backward[i] = π(n-1...i)A[i]
    int[] backward = new int[n];
    backward[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      backward[i] = backward[i + 1] * nums[i];
    }

    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
      res[i] = 1;
      if (i > 0)
        res[i] *= forward[i - 1];
      if (i < n - 1)
        res[i] *= backward[i + 1];
    }

    return res;
  }
}
