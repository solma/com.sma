"""
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
"""

from src.main.python.alg.label import Label

Label(Label.BFS, Label.DynamicProgramming, Label.LeetCode)

class CoinChange(object):
  def coinChange(self, coins, amount):
    dp = [0] + [-1] * amount
    for i in range(amount):
      if dp[i] == -1:
        continue
      for c in coins:
        if c + i > amount:
          continue
        if dp[i + c] == -1 or dp[c + i] > dp[i] + 1:
          dp[i + c] = dp[i] + 1
    return dp[amount]

  def coinChangeBFS(self, coins, amount):
    import collections
    steps = collections.defaultdict(int)
    queue = collections.deque([0])
    steps[0] = 0
    while queue:
      front = queue.popleft()
      level = steps[front]
      if front == amount:
        return level
      for c in coins:
        if front + c > amount:
          continue
        if front + c not in steps:
          queue.append(front + c)
          steps[front + c] = level + 1
    return -1

print CoinChange().coinChangeBFS([3,7,405,436], 8839)

