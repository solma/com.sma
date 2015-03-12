package com.shuoma.alg.number;

// source: weixin daizhiguizhong
// B[i]=A[0]*A[1]*...A[i-1]*A[i+1]*...A[n]
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
public class ArrayTransformationWithoutDivision {
  public static void main(String[] args) {
    new ArrayTransformationWithoutDivision().main();
  }

  public void main() {
    int[] A = new int[] {1, 5, 2, 4, 3, 6};
    int[] B = transform(A);
    int[] C = transform1(A);
    assertArrayEquals(B, C);
    System.out.println(Arrays.toString(B));
    System.out.println(Arrays.toString(C));
  }

  public int[] transform1(int[] A) {
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

  public int[] transform(int[] A) {
    int i, n = A.length;
    int B[] = new int[n];
    B[0] = A[0];
    for (i = 1; i < n - 1; i++) {
      B[i] = A[i] * B[i - 1];
    }

    if (n >= 2) {
      B[n - 1] = B[n - 2];
      int C = A[n - 1];
      for (i = n - 2; i >= 1; i--) {
        B[i] = C * B[i - 1];
        C *= A[i];
      }
      B[0] = C;
    }

    return B;
  }
}
