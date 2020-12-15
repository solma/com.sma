"""
Given an integer array nums, find the sum of the elements between indices i and j (i<=j),inclusive.
Example:
Given nums = [-2, 0, 3, -5, 2, -1]
sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
"""

from alg.label import Label

Label(Label.Array, Label.DynamicProgramming, Label.LinearTime, Label.LeetCode)

class NumArray(object):
  def __init__(self, nums):
    self._sum = nums
    for i in range(1, len(nums)):
      self._sum[i] += self._sum[i - 1]

  def sumRange(self, i, j):
    return self._sum[j] - (0 if i == 0 else self._sum[i - 1])


from unittest import TestCase
class TestNumArray(TestCase):
  def test_sumRange(self):
    na = NumArray([-2, 0, 3, -5, 2, -1])
    self.assertEqual(1, na.sumRange(0, 2))
    self.assertEqual(-1, na.sumRange(2, 5))
    self.assertEqual(-3, na.sumRange(0, 5))
