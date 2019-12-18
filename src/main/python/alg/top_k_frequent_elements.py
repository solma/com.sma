"""
Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:
You may assume k is always valid, 1 <= k <= number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
"""
from alg.label import Label
Label(Label.Hash, Label.Heap, Label.LeetCode)

class TopKFrequentElements(object):
  def topKFrequent(self, nums, K):
    from collections import defaultdict
    import heapq as hq
    d = defaultdict(int)
    for n in nums:
      d[n] += 1
    heap = [(-v, k) for k, v in d.iteritems()]
    hq.heapify(heap)
    ret = []
    while len(ret) < K:
      ret.append(hq.heappop(heap)[1])
    return ret

ins = TopKFrequentElements()
print(ins.topKFrequent([1,1,1,2,2,3], 2))
