"""
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s.

Example 1:
Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation:
[1,1,1,0,0,*1*,1,1,1,1,*1*]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Example 2:
Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation:
[0,0,1,1,*1*,*1*,1,1,1,*1*,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
"""
from typing import List

from alg.label import Label

Label(Label.Hash, Label.Interview, Label.SlidingWindow, Label.Subarray)


class Solution:
  def longestOnes(self, A: List[int], K: int) -> int:
    from collections import Counter
    c = Counter(A)
    if c[0] <= K:
      return len(A)
    zero_pos = []
    cur_len, ret = 0, 0
    for i, num in enumerate(A):
      if num == 1:
        cur_len += 1
      else:
        zero_pos.append(i)
        if len(zero_pos) == K + 1:
          ret = max(ret, cur_len)
          oldest = zero_pos.pop(0)
          cur_len = i - oldest
    ret = max(ret, cur_len)
    return ret


ins = Solution()
for arr, K, expected in [
  ([1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], 2, 6),
  ([0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1], 3, 10),
  ([1, 1, 0, 0, 1], 2, 5),
  ([1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], 2, 6)
]:
  actual = ins.longestOnes(arr, K)
  if actual != expected:
    print(arr, K, actual, expected)
