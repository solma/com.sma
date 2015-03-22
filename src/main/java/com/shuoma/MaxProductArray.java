package com.shuoma;
import java.util.Arrays;

public class MaxProductArray {
  public static void main(String[] args) {
    for (int i = 0; i < 1; i++) {
      //int[] A = RandomUtil.genRandomArray(10, 10, false, true);
      int[] A = {3, -2, 5};
      int[][] res = new int[2][3];
      res[0] = maxProductSimple(A);
      res[1] = maxProductDP(A);
      if (res[0][2] != res[1][2]){
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
      }
    }
  }

  // return range and the product
  static int[] maxProductSimple(int[] A) {
    int n = A.length;
    int forwardProduct = 1;
    int backwardProduct = 1;
    int forwardStartIdx = 0;
    int backwardEndIdx = n - 1;
    int [] ret = new int[3];
    for (int i = 0; i < n; i++) {
      forwardProduct *= A[i];
      backwardProduct *= A[n - 1 - i];
      if (forwardProduct >= backwardProduct && forwardProduct > ret[2]) {
        ret[2] = forwardProduct;
        ret[1] = i;
        ret[0] = forwardStartIdx;
      }
      if (backwardProduct > forwardProduct && backwardProduct > ret[2]) {
        ret[2] = backwardProduct;
        ret[1] = backwardEndIdx;
        ret[0] = n - i - 1;
      }
      if (forwardProduct == 0) {
        forwardProduct = 1;
        forwardStartIdx = i + 1;
      }
      if (backwardProduct == 0) {
        backwardProduct = 1;
        backwardEndIdx = n - 1 - i - 1;
      }
    }
    return ret;
  }

  static int[] maxProductDP(int[] A) {
    int n = A.length;
    int[] subarray = new int[2];
    // dp[i][0]: min product of subarray ending at i
    // dp[i][1]: max product of subarray ending at i
    // sIdx[i][0]: start idx for min subarray ending at i
    // sIdx[i][1]: start idx for max subarray ending at i
    int[][] dp = new int[n][2];
    int[][] sIdx = new int[n][2];
    int ret = Integer.MIN_VALUE;
    int productWithPrevMax, productWithPrevMin;
    for (int i = 0; i < n; i++) {
      dp[i][0] = dp[i][1] = A[i];
      sIdx[i][0] = sIdx[i][1] = i;
      if (i > 0) {
        productWithPrevMin = A[i] * dp[i - 1][0];
        productWithPrevMax = A[i] * dp[i - 1][1];

        // update dp[i][0] and sIdx[i][0]
        if (productWithPrevMax < dp[i][0] && productWithPrevMax <= productWithPrevMin) {
          dp[i][0] = productWithPrevMax;
          sIdx[i][0] = sIdx[i - 1][1];
        } else {
          if (productWithPrevMin < dp[i][0] && productWithPrevMin < productWithPrevMax) {
            dp[i][0] = productWithPrevMin;
            sIdx[i][0] = sIdx[i - 1][0];
          }
        }

        // update dp[i][1] and sIdx[i][1]
        if (productWithPrevMax > dp[i][1] && productWithPrevMax >= productWithPrevMin) {
          dp[i][1] = productWithPrevMax;
          sIdx[i][1] = sIdx[i - 1][1];
        } else {
          if (productWithPrevMin > dp[i][0] && productWithPrevMin > productWithPrevMax) {
            dp[i][1] = productWithPrevMin;
            sIdx[i][1] = sIdx[i - 1][0];
          }
        }

        // dp[i][0]=Math.min(dp[i][0], Math.min(A[i]*dp[i-1][0], A[i]*dp[i-1][1]) );
        // dp[i][1]=Math.max(dp[i][1], Math.max(A[i]*dp[i-1][0], A[i]*dp[i-1][1]) );
      }
      if (dp[i][1] > ret) {
        ret = dp[i][1];
        subarray[1] = i; // update end index
        subarray[0] = sIdx[i][1];// update start index
      }
      //System.out.println("i = " + i + " " + Arrays.toString(dp[i]) + " " + Arrays.toString(sIdx[i]));
    }

    //System.out.println(Arrays.toString(subarray));
    return new int[]{subarray[0], subarray[1], ret};
  }
}
