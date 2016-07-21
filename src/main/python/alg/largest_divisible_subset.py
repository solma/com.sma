"""
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
If there are multiple solutions, return any subset is fine.

Example 1:
nums: [1,2,3]
Result: [1,2] (of course, [1,3] will also be ok)

Example 2:
nums: [1,2,4,8]
Result: [1,2,4,8]
"""

from alg.label import Label
Label(Label.DynamicProgramming, Label.QuadraticTime, Label.LeetCode)

class LargestDivisibleSubset(object):
  def largestDivisibleSubset(self, nums):
    n = len(nums)
    if n < 1:
      return []

    largest_divisible_subset = [nums[0]]
    if n < 2:
      return largest_divisible_subset

    nums = sorted(nums)
    dp = [[nums[i]] for i in range(n)]
    for i in range(1, n):
      for j in range(0, i):
        if nums[i] % nums[j] != 0:
          continue
        if len(dp[j]) >= len(dp[i]):
          dp[i] = dp[j] + [nums[i]]
        if len(largest_divisible_subset) < len(dp[i]):
          largest_divisible_subset = dp[i]
    return largest_divisible_subset
