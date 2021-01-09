"""
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array arr, return the length of the longest subarray, which is a mountain.
Return 0 if there is no mountain subarray.

Example 1:
Input: arr = [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

Example 2:
Input: arr = [2,2,2]
Output: 0
Explanation: There is no mountain.

Constraints:
1 <= arr.length <= 104
0 <= arr[i] <= 104
"""
from typing import List

from alg.label import *

Label(Label.Array, Label.TwoPointers, Label.LeetCode)


class Solution:
  def longestMountain(self, arr: List[int]) -> int:
    l, ret, n = 0, 0, len(arr)

    while l < n - 2:
      while l < n - 2 and arr[l] >= arr[l + 1]:
        l += 1
      if l == n - 2:
        break
      m = l + 1
      while m < n - 1 and arr[m] < arr[m + 1]:
        m += 1
      if m == n - 1:
        break
      r = m + 1
      if arr[r] < arr[m]:
        while r < n - 1 and arr[r] > arr[r + 1]:
          r += 1
        ret = max(ret, r - l + 1)
      l = r
    return ret

verify_solution(Solution(), 'longestMountain', [
  ([0, 2, 2], 0),
  ([2, 1, 4, 7, 3, 2, 5], 5),
  [[2, 2, 2], 0],
  [[3, 2, 5, 1, 6, 7, 9, 8, 2, 5, 1], 6],
])
