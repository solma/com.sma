package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, source = LeetCode)
public class BestTimeToBuyandSellStock {
  public int maxProfit(int[] prices) {
    if (prices.length == 0)
      return 0;
    int min = prices[0], ret = 0;
    for (int i = 1; i < prices.length; i++) {
      min = Math.min(prices[i], min);
      ret = Math.max(prices[i] - min, ret);
    }
    return ret;
  }
}
