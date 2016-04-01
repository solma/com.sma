package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

/***
 * Allow unlimited # of transactions
 */
@Tag(dss = Array, references = LeetCode)
public class BestTimeToBuyandSellStockII {
  public int maxProfit(int[] prices) {
    if (prices.length == 0)
      return 0;
    int prev = prices[0], ret = 0;
    for (int i = 1; i < prices.length; i++) {
      ret += Math.max(0, prices[i] - prev);
      prev = prices[i];
    }
    return ret;
  }
}
