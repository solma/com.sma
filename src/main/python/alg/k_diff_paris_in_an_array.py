"""
Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
0 <= i, j < nums.length
i != j
|nums[i] - nums[j]| == k
Notice that |val| denotes the absolute value of val.

Example 1:
Input: nums = [3,1,4,1,5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:
Input: nums = [1,2,3,4,5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:
Input: nums = [1,3,1,5,4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

Example 4:
Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
Output: 2

Example 5:
Input: nums = [-1,-2,-3], k = 1
Output: 2

Constraints:

1 <= nums.length <= 104
-107 <= nums[i] <= 107
0 <= k <= 107
"""
from typing import List

from alg.label import *

Label(Label.Array, Label.TwoPointers, Label.LeetCode)


class Solution:
  def findPairs(self, nums: List[int], k: int) -> int:
    ret = 0

    if k == 0:
      from collections import Counter
      c = Counter(nums)
      for _, v in c.items():
        if v > 1:
          ret += 1

    arr = sorted(list(set(nums)))
    l = 0
    # print(ret, arr)
    for r in range(len(arr)):
      while arr[r] - arr[l] > k:
        l += 1
      if r != l and arr[r] - arr[l] == k:
        ret += 1
    return ret

verify_solution(Solution(), 'findPairs', [
  ([3, 1, 4, 1, 5], 2, 2),
  ([1, 3, 1, 5, 4], 0, 1),
  ([-1, -2, -3], 1, 2),
  ([1, 2, 4, 4, 3, 3, 0, 9, 2, 3], 3, 2),
  ([1, 2, 3, 4, 5], 1, 4),
])
