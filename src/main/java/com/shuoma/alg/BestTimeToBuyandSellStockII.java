package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

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
