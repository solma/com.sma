"""
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i <= j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
"""
import bisect

from src.main.python.alg.label import Label

Label(Label.BinaryIndexedTree, Label.BitManipulation, Label.DivideConquer, Label.LeetCode)

class CountOfRangeSum(object):
  def countRangeSum(self, nums, lower, upper):
    sums = nums[:]
    for x in range(1, len(sums)):
      sums[x] += sums[x - 1]
    osums = sorted(set(sums))
    ft = FenwickTree(len(osums))
    ans = 0
    for sumi in sums:
      left = bisect.bisect_left(osums, sumi - upper)
      right = bisect.bisect_right(osums, sumi - lower)
      ans += ft.sum(right) - ft.sum(left) + (lower <= sumi <= upper)
      ft.add(bisect.bisect_right(osums, sumi), 1)
    return ans

class FenwickTree(object):
  """
  tree[idx] stores the cumulative sum in range [idx - 2^r + 1, idx], where
  r is the position of the least significant bit 1 in binary representation of idx.
  """
  def __init__(self, n):
    self.n = n
    self.sums = [0] * (n + 1)
  def lowbit(self, x):
    return x & -x
  def add(self, x, val):
    """
    update: only care left subtree
    """
    while x <= self.n:
      self.sums[x] += val
      x += self.lowbit(x)
  def sum(self, x):
    """
    lookup: only care right subtree
    """
    res = 0
    while x > 0:
      res += self.sums[x]
      x -= self.lowbit(x)
    return res
