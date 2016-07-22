package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Complexity.Linear;
import static com.sma.annotation.Tag.DataStructure.Subarray;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Trick.ForwardAndBackwardScan;

@Tag(algs = DynamicProgramming, dl = D3,
    timecomplexity = Linear, dss = Subarray, references = LeetCode, tricks = ForwardAndBackwardScan)
public class MaxProductArray {

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
