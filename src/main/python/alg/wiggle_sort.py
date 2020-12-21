"""
Given an unsorted array nums, reorder it such that
nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time (average time complexity) and/or
in-place with O(1) extra space?
"""

from alg.label import Label
Label(Label.LinearithmicTime, Label.Greedy, Label.LinearTime, Label.LeetCode)


class WiggleSort(object):
  def wiggleSort1(self, nums):  # O(nlogn)
    size = len(nums)
    sorted_nums = sorted(nums)
    for x in list(range(1, size, 2)) + list(range(0, size, 2)):
      nums[x] = sorted_nums.pop()
    return nums

  def wiggleSort(self, nums):  # O(n)
    for i in range(1, len(nums)):
      if (i % 2 == 1 and nums[i] < nums[i - 1]
          or i % 2 == 0 and nums[i] > nums[i - 1]):
        nums[i - 1], nums[i] = nums[i], nums[i - 1]
    return nums
