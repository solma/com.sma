"""
Given an array which consists of non-negative integers and an integer m,
you can split the array into m non-empty continuous subarrays.
Write an algorithm to minimize the largest sum among these m subarrays.

Note:
Given m satisfies the following constraint: 1 <= m <= length(nums) <= 14,000.

Examples:

Input:
nums = [1,2,3,4,5]
m = 2

Output:
9

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5],
where the largest sum among the two subarrays is only 9.
"""
from alg.label import Label
Label(Label.BinarySearch, Label.DynamicProgramming, Label.LeetCode)

class SplitArrayLargestSum(object):
  # O(n^2m)
  def splitArray(self, nums, m):
    import sys
    n = len(nums)

    # initialize
    dp = [[sys.maxint] * n for _ in range(2)]
    sum = 0
    for i in range(n):
      dp[1][i] = nums[i] + sum
      sum = dp[1][i]

    for i in range(2, m + 1):
      col = i % 2
      for j in range(i - 1, n):
        sum = 0
        for k in range(j, j - i, -1):
          sum += nums[k]
          dp[col % 2][j] = min(dp[col % 2][j], max(sum, dp[(col - 1) % 2][k - 1]))
        # print dp
    return dp[m % 2][n - 1]

  # O(nlog(sum(nums)))
  def splitArrayBinarySearch(self, nums, m):
    size = len(nums)
    high = sum(nums)
    low = high / m
    while low <= high:
      mid = (low + high) / 2
      n = m
      cnt = 0
      valid = True
      for x in range(size):
        if nums[x] > mid:
          valid = False
          break
        if cnt + nums[x] > mid:
          n -= 1
          cnt = 0
        cnt += nums[x]
        if n == 0:
          valid = False
          break
      if valid:
        high = mid - 1
      else:
        low = mid + 1
    return low

ins = SplitArrayLargestSum()
print(ins.splitArray([1, 2, 3, 4, 5, 6, 7, 8, 9], 3))


