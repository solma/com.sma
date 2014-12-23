package com.shuoma.alg.number;

// source: weixin daizhiguizhong
// B[i]=A[0]*A[1]*...A[i-1]*A[i+1]*...A[n]
public class ArrayTransformationWithoutDivision {
  public static void main(String[] args) {
    new ArrayTransformationWithoutDivision().main();
  }

  public void main() {
    int[] A = new int[] {1, 5, 2, 4, 3, 6};
    int[] B = transform(A);
    int i;
    for (i = 0; i < B.length; i++) {
      System.out.print(B[i] + " ");
    }
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
