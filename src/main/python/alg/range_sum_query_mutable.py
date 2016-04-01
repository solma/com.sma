"""
Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
"""

from alg.label import Label

Label(Label.BinarySearchTree, Label.SegmentTree, Label.LeetCode)

class NumArray(object):
  def __init__(self, nums):
    l = len(nums)
    self._nums = nums
    self._root = self._build_segment_tree(l, 0, l - 1)

  def update(self, i, val):
    delta = val - self._nums[i]
    self._nums[i] = val
    self._update_sum(self._root, i, delta)

  def sumRange(self, i, j):
    return self._sum_range(self._root, i, j)

  def _build_segment_tree(self, l, i, j):
    if i > j:
      return None
    if i == j:
      return SegmentTreeNode(self._nums[i], (i, i))
    m = i + ((j - i) >> 1)
    left = self._build_segment_tree(l, i, m)
    right = self._build_segment_tree(l, m + 1, j)
    return SegmentTreeNode(left.sum + right.sum, (i, j), left, right)

  def _update_sum(self, node, i, delta):
    node.sum += delta
    if node.left and node.left.range[0] <= i <= node.left.range[1]:
      self._update_sum(node.left, i, delta)
    if node.right and node.right.range[0] <= i <= node.right.range[1]:
      self._update_sum(node.right, i, delta)

  def _sum_range(self, node, i, j):
    if i <= node.range[0] <= node.range[1] <= j:
      return node.sum
    if node.range[0] <= i <= j <= node.range[1]:
      return self._sum_range(node.left, i, j) + self._sum_range(node.right, i, j)
    if node.range[1] < i or j < node.range[0]:
      return 0
    return self._sum_range(node, max(i, node.range[0]), min(j, node.range[1]))


class SegmentTreeNode(object):
  def __init__(self, value, idx_range, left = None, right = None):
    self.sum = value
    self.left = left
    self.right = right
    self.range = idx_range
