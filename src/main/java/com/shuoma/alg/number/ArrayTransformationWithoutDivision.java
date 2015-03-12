package com.shuoma.alg.number;

// source: weixin daizhiguizhong
// B[i]=A[0]*A[1]*...A[i-1]*A[i+1]*...A[n]

public class ArrayTransformationWithoutDivision {

 public int[] transform(int[] A) {
    int n = A.length;
    if (n < 2) return A;
    int[] res = new int[n];
    int[] forward = new int[n];
    int[] backward = new int[n];

    // forward[i] = π(0...i)A[i]
    forward[0] = A[0];
    for(int i = 1; i < n; i++) {
      forward[i] = forward[i - 1] * A[i];
    }

    // backward[i] = π(n-1...i)A[i]
    backward[n - 1] = A[n - 1];
    for(int i = n-2; i >= 0; i--) {
      backward[i] = backward[i + 1] * A[i];
    }

    for (int i = 0; i < n; i++) {
      res[i] = 1;
      if (i > 0) res[i] *= forward[i - 1];
      if (i < n - 1) res[i] *= backward[i + 1];
    }

    return res;
  }


}
