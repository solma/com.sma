"""
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
"""
from alg.label import *
Label(Label.Subarray, Label.Stack, Label.Sorting, Label.LeetCode)

class Solution(object):
  def findUnsortedSubarray(self, nums):
    n = len(nums)
    assert n >= 1
    l, r = n, 0
    li = []
    for i, x in enumerate(nums):
      while len(li) > 0 and nums[li[-1]] > x:
        l = min(l, li.pop())
      li.append(i)
    li = []
    for i in range(n - 1, -1, -1):
      while len(li) > 0 and nums[li[-1]] < nums[i]:
        r = max(r, li.pop())
      li.append(i)
    return r - l + 1 if r > l else 0

ins = Solution()
for case in [
  [2, 6, 4, 8, 10, 9, 15],
  [1, 3, 2, 2, 2],
  [1, 2, 3, 2, 2, 5, 5, 2],
  [1, 2, 3, 3, 3],
  [2, 3, 3, 2, 4],
]:
  print(ins.findUnsortedSubarray(case))
