"""
Given a collection of intervals, find the minimum number of intervals
you need to remove to make the rest of the intervals non-overlapping.

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
"""
from typing import List

from alg.label import *

Label(Label.Interval, Label.Greedy, Label.LeetCode)


class Solution:
  def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
    if not intervals:
      return 0
    intervals = sorted(intervals)
    ret = 0
    prev_end = intervals[0][1]
    for interval in intervals[1:]:
      if interval[0] < prev_end:
        ret += 1
        prev_end = min(prev_end, interval[1])
      else:
        prev_end = interval[1]
    return ret


verify_solution(Solution(), 'eraseOverlapIntervals', [
  ([[1, 2], [2, 3], [3, 4], [1, 3]], 1),
  ([[1, 2], [1, 2], [1, 2]], 2),
  ([[1, 2], [2, 3]], 0),
])
