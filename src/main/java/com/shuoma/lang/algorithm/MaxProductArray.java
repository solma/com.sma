package algorithm;

import java.util.Arrays;

public class MaxProductArray {
  public static void main(String[] args) {
    maxProduct(new int[] {2, -5, 3, -2, 6});
  }

  public static int maxProduct(int[] A) {
    int n = A.length;
    int[] subarray = {0, 0};
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
      dp[i][2] = i; // subarray starting from itself
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
        if (i > 0) {
          subarray[1] = i; // update end index
          subarray[0] = sIdx[i][1];// update start index
        }
      }

    }

    System.out.println(Arrays.toString(subarray));
    return ret;
  }
}
