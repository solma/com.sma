"""
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
"""
import heapq
from alg.label import Label
Label(Label.Heap, Label.LeetCode)

class FindKPairsWithSmallestSum(object):
  def kSmallestPairs(self, nums1, nums2, k):
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

ins = FindKPairsWithSmallestSum()
print ins.kSmallestPairs([1, 2, 3], [2, 5, 6], 5)
