"""
A split of an integer array is good if:

The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from
left to right. The sum of the elements in left is less than or equal to the sum of the elements in mid,
and the sum of the elements in mid is less than or equal to the sum of the elements in right.
Given nums, an array of non-negative integers, return the number of good ways to split nums.
As the number may be too large, return it modulo 109 + 7.

Example 1:
Input: nums = [1,1,1]
Output: 1
Explanation: The only good way to split nums is [1] [1] [1].

Example 2:
Input: nums = [1,2,2,2,5,0]
Output: 3
Explanation: There are three good ways of splitting nums:
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]

Example 3:
Input: nums = [3,2,1]
Output: 0
Explanation: There is no good way to split nums.

Constraints:
3 <= nums.length <= 105
0 <= nums[i] <= 104
"""
from itertools import accumulate
from typing import List

from alg.label import *

Label(Label.Subarray, Label.TwoPointers, Label.LeetCode)


class Solution:
  def waysToSplit(self, nums: List[int]) -> int:
    sz, res, j, k = len(nums), 0, 0, 0
    nums = list(accumulate(nums))
    # i is the boundary of the left subarray
    # For each i, we find the minimum (j) and maximum (k) boundaries of the second subarray:
    # nums[j] >= 2 * nums[i]
    # nums[sz - 1] - nums[k] >= nums[k] - nums[i]
    for i in range(sz - 2):
      while j <= i or (j < sz - 1 and nums[j] < nums[i] * 2):
        j += 1
      while k < j or (k < sz - 1 and nums[k] - nums[i] <= nums[-1] - nums[k]):
        k += 1
      res = (res + k - j) % 1000000007
    return res


verify_solution(Solution(), 'waysToSplit', [
  ([3, 2, 1], 0),
  ([1, 2, 2, 2, 5, 0], 3),
  ([1, 1, 1], 1),
])
