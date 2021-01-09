"""
Given an array A, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
- left and right are non-empty.
- left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.

Example 1:

Input: [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]
"""
from math import inf
from typing import List

from alg.label import *

Label(Label.Array, Label.Greedy, Label.LeetCode)


class Solution:
  def partitionDisjoint(self, A: List[int]) -> int:
    n = len(A)
    large, small = [0] * n, [0] * n
    l, s = -inf, inf
    for i in range(n):
      l, s = max(l, A[i]), min(s, A[n - 1 - i])
      large[i], small[n - 1 - i] = l, s
    for i in range(n):
      if large[i] <= small[i + 1]:
        return i + 1
    return -1


verify_solution(Solution(), 'partitionDisjoint', [
  ([5, 0, 3, 8, 6], 3),
  ([1, 1, 1, 0, 6, 12], 4),
])
