"""
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons.If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
Find the maximum coins you can collect by bursting the balloons wisely.

Note:
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 <= n <= 500, 0 <= nums[i] <= 100

Example:
Given [3, 1, 5, 8]
Return 167
  nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
  coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
"""

from src.main.python.alg.label import Label

Label(Label.DivideConquer, Label.DynamicProgramming, Label.LeetCode)

class BurstBallons(object):
  def maxCoins(self, nums):
    nums = [1] + [i for i in nums if i > 0] + [1]
    n = len(nums)
    ret = [[0] * n for _ in range(n)]
    for size in range(1, n - 1):
      for l in range(0, n - size - 1):
        r = l + size + 1
        for m in range(l + 1, r):
          ret[l][r] = max(ret[l][r], ret[l][m] + ret[m][r] + nums[l] * nums[m] * nums[r])
    return ret[0][-1]

print BurstBallons().maxCoins([3, 1, 5, 8])
