"""
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays
where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
"""
from typing import List

from alg.label import *

Label(Label.Subarray, Label.BinarySearch, Label.TwoPointers, Label.LeetCode)


class Solution(object):
  def numSubarrayProductLessThanK(self, nums, k):
    # loop invariant is that left is the smallest value so that the product in the window
    # prod = nums[left] * nums[left + 1] * ... * nums[right] is less than k.
    # For every right, we update left and prod to maintain this invariant.
    # Then, the number of intervals with subarray product less than k and with right-most coordinate
    # right, is right - left + 1. We'll count all of these for each value of right.
    if k <= 1: return 0
    prod = 1
    ans = left = 0
    for right, val in enumerate(nums):
      prod *= val
      while prod >= k:
        prod /= nums[left]
        left += 1
      ans += right - left + 1
    return ans

  # take the log, accumulate the array, and convert the problem to two-minus
  def numSubarrayProductLessThanKBinarySearch(self, nums: List[int], k: int) -> int:
    import math, bisect
    if k == 0: return 0
    k = math.log(k)

    prefix = [0]
    for x in nums:
      prefix.append(prefix[-1] + math.log(x))

    ans = 0
    for i, x in enumerate(prefix):
      j = bisect.bisect(prefix, x + k - 1e-9, i + 1)
      ans += j - i - 1
    return ans

verify_solution(Solution(), 'numSubarrayProductLessThanK', [
  ([10, 5, 2, 6], 100, 8)
])
