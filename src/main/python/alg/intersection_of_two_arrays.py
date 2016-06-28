"""
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:


Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to num2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot
load all elements into the memory at once?
"""

from alg.label import Label

Label(Label.Array, Label.Greedy, Label.LeetCode)

class IntersectionOfTwoArrays(object):
  def intersectII(self, nums1, nums2):
    """
    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.
    """
    from collections import Counter
    import itertools
    c1, c2 = Counter(nums1), Counter(nums2)
    common_keys = list(set(nums1) & set(nums2))
    return list(itertools.chain.from_iterable(map(lambda x: [x] * min(c1[x], c2[x]), common_keys)))

  def intersectI(self, nums1, nums2):
    """
    Each element in the result must be unique.
    The result can be in any order.
    """
    return list(set(nums1) & set(nums2))
