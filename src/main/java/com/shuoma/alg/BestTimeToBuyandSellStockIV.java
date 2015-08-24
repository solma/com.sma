package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

/**
 * Allow at most K transactions
 */
@Tag(algs = DynamicProgramming, dl = D3, dss = Subarray, references = LeetCode)
public class BestTimeToBuyandSellStockIV {

  /**
   * @param k number of transactions
   * @param prices
   * @return max profit
   */
  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if (n < 1) { return 0; }
    if (k >= n / 2) {
      int sum = 0;
      for (int i = 1; i < n; i++) {
        sum += Math.max(0, prices[i] - prices[i - 1]);
      }
      return sum;
    }

    return dp1d(k, prices);
  }

  int dp1d(int k, int[] prices) {
    int[] lastTransactionOnLastDay = new int[k + 1];
    int[] maxProfit = new int[k + 1];

    int n = prices.length;
    for (int i = 1; i < n; i++) {
      int diff = prices[i] - prices[i - 1];
      for (int j = k; j >= 1; j--) {
        lastTransactionOnLastDay[j] = Math.max(lastTransactionOnLastDay[j] + diff,
            maxProfit[j - 1] + Math.max(0, diff));
        maxProfit[j] = Math.max(maxProfit[j], lastTransactionOnLastDay[j]);
      }
    }
    return maxProfit[k];
  }

  int dp2d(int k, int[] prices) {
    int n = prices.length;
    int[][] lastTransactionOnLastDay = new int[n][k + 1];
    int[][] maxProfit = new int[n][k + 1];

    for (int i = 1; i < n; i++) {
      int diff = prices[i] - prices[i - 1];
      for (int j = 1; j <= k; j++) {
        lastTransactionOnLastDay[i][j] = Math.max(lastTransactionOnLastDay[i - 1][j] + diff,
            maxProfit[i - 1][j - 1] + Math.max(0, diff));
        maxProfit[i][j] = Math.max(maxProfit[i - 1][j], lastTransactionOnLastDay[i][j]);
      }
    }
    return maxProfit[n - 1][k];
  }
}
