"""
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
"""
from alg.label import *

Label(Label.Heap, Label.LeetCode)


class Solution(object):
  def kSmallestPairs(self, nums1, nums2, k):
    import heapq
    queue = []

    def push(i, j):
      if i < len(nums1) and j < len(nums2):
        heapq.heappush(queue, (nums1[i] + nums2[j], i, j))

    push(0, 0)
    pairs = []
    while queue and len(pairs) < k:
      _, i, j = heapq.heappop(queue)
      pairs.append((nums1[i], nums2[j]))
      push(i, j + 1)
      if j == 0:
        push(i + 1, 0)
    return pairs


ins = Solution()
for arr1, arr2, k, expected in [
  ([1, 7, 11], [2, 4, 6], 3, [(1, 2), (1, 4), (1, 6)]),
  ([2, 4, 6], [1, 7, 11], 3, [(2, 1), (4, 1), (6, 1)]),
]:
  actual = ins.kSmallestPairs(arr1, arr2, k)
  if actual != expected:
    print(arr1, arr2, k, expected, actual)
