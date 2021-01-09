"""
You are given an integer array nums and an integer x. In one operation, you can
either remove the leftmost or the rightmost element from the array nums and subtract its value from x.
Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.


Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements
(5 operations in total) to reduce x to zero.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
"""
from typing import List

from alg.label import *

Label(Label.Array, Label.TwoPointers, Label.DynamicProgramming, Label.LeetCode)


class Solution:
  def solve(self, nums, l, r, x):
    if x == 0:
      return 0
    if l > r:
      return -1
    left_sol = self.solve(nums, l + 1, r, x - nums[l])
    right_sol = self.solve(nums, l, r - 1, x - nums[r])
    if left_sol != -1 and right_sol != -1:
      return min(left_sol, right_sol) + 1
    if left_sol == -1 and right_sol == -1:
      return -1
    return left_sol + 1 if right_sol == -1 else right_sol + 1

  def minOperationsRc(self, nums: List[int], x: int) -> int:
    return self.solve(nums, 0, len(nums) - 1, x)

  def minOperations(self, nums: List[int], x: int) -> int:
    # Sliding Window : find the longest window that val = sum(nums)-target,
    # once it is longest, then `n-windowSize` element will be the minimum number
    target = sum(nums) - x
    if target == 0:
      return len(nums)
    currSum = left = maxWindowSize = 0

    for right in range(len(nums)):
      currSum += nums[right]
      while left < right and currSum > target:
        currSum -= nums[left]
        left += 1
      if currSum == target:
        maxWindowSize = max(maxWindowSize, right - left + 1)

    return -1 if maxWindowSize == 0 else len(nums) - maxWindowSize

  def minOperationsDp(self, nums: List[int], x: int) -> int:
    def update(dp, i, j, k, prev):
      if dp[i][j][k] == -1:
        dp[i][j][k] = prev + 1
      else:
        dp[i][j][k] = min(dp[i][j][k], prev + 1)

    dp = []
    n = len(nums)
    for i in range(n):
      dp.append([])
      for j in range(n):
        dp[i].append([])
        for k in range(x + 1):
          if k == 0:
            dp[i][j].append(0)
          elif i == j and nums[i] == k:
            dp[i][j].append(1)
          else:
            dp[i][j].append(-1)

    for k in range(x + 1):
      for ls in range(1, n + 1):
        for i in range(n - ls + 1):
          j = i + ls - 1
          if i < n - 1 and k >= nums[i] and dp[i + 1][j][k - nums[i]] != -1:
            update(dp, i, j, k, dp[i + 1][j][k - nums[i]])
          if j >= 1 and k >= nums[j] and dp[i][j - 1][k - nums[j]] != -1:
            update(dp, i, j, k, dp[i][j - 1][k - nums[j]])
          # print(dp)
    return dp[0][n - 1][x]

verify_solution(Solution(), 'minOperationsDp', [
  ([1, 2, 3], 3, 1),
  ([1, 1, 4, 2, 3], 5, 2),
  ([5, 6, 7, 8, 9], 4, -1),
  ([3, 2, 20, 1, 1, 3], 10, 5),
])
