"""
A sequence of numbers is called a wiggle sequence if the differences between
successive numbers strictly alternate between positive and negative.
The first difference (if one exists) may be either positive or negative.
A sequence with fewer than two elements is trivially a wiggle sequence.
For example, [1,7,4,9,2,5] is a wiggle sequence because the differences
(6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5]
and [1,7,4,5,5] are not wiggle sequences, the first because its first two
differences are positive and the second because its last difference is zero.
Given a sequence of integers, return the length of the longest subsequence that
is a wiggle sequence. A subsequence is obtained by deleting some number of
elements (eventually, also zero) from the original sequence, leaving the
remaining elements in their original order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length.
One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?
"""

from alg.label import *
Label(Label.Greedy, Label.LinearTime,
      Label.DynamicProgramming, Label.QuadraticTime,
      Label.LeetCode)

class WiggleSequence(object):
  # Greedy
  def wiggleMaxLength(self, nums):
    size = len(nums)
    if size < 2: return size
    delta = nums[1] - nums[0]
    ans = 1 + (delta != 0)
    for x in range(2, size):
      newDelta = nums[x] - nums[x - 1]
      if newDelta != 0 and newDelta * delta <= 0:
        ans += 1
        delta = newDelta
    return ans

  # DP
  def wiggleMaxLength1(self, nums):
    size = len(nums)
    inc, dec = [1] * size, [1] * size
    for x in range(size):
      for y in range(x):
        if nums[x] > nums[y]:
          inc[x] = max(inc[x], dec[y] + 1)
        elif nums[x] < nums[y]:
          dec[x] = max(dec[x], inc[y] + 1)
    return max(inc + dec) if size else 0

ins = WiggleSequence()
print(ins.wiggleMaxLength([1, 3, 5, 7, 2, 6]))
