package com.shuoma.alg;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class ProductOfArrayExceptSelfTest extends TestCase {

  public void testTransform() throws Exception {
    ProductOfArrayExceptSelf ins = new ProductOfArrayExceptSelf();
    int[] A = new int[] {1, 5, 2, 4, 3, 6};
    int[] B = transformDummy(A);
    int[] C = ins.transform(A);
    if (!Arrays.equals(B, C)) {
      System.out.println(Arrays.toString(B));
      System.out.println(Arrays.toString(C));
    }
  }

  @Test
  int[] transformDummy(int[] A) {
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
