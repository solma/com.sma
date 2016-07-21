"""
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
"""

from alg.label import Label

Label(Label.Array, Label.DynamicProgramming, Label.LinearTime, Label.LeetCode)

class Solution(object):
  def maxProfit(self, prices):
    size = len(prices)
    if not size:
      return 0
    buys = [None] * size
    sells = [None] * size
    sells[0], buys[0] = 0, -prices[0]
    for x in range(1, size):
      delta = prices[x] - prices[x - 1]
      sells[x] = max(buys[x - 1] + prices[x], sells[x - 1] + delta)
      buys[x] = max(buys[x - 1] - delta, sells[x - 2] - prices[x] if x > 1 else None)
    return max(sells)
