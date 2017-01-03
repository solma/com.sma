"""
Given a n x n matrix where each of the rows and columns are sorted in ascending order,
find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
"""

from alg.label import Label
Label(Label.BinarySearch, Label.Heap, Label.LinearithmicTime, Label.LeetCode)

class KthSmallestElementInASortedMatrix(object):
  def kthSmallest(self, matrix, k):
    from heapq import heappush, heappop
    heap = []
    row = len(matrix)
    col = len(matrix[0])

    for i in range(col):
      heappush(heap, (matrix[0][i], (0, i)))

    for j in range(k - 1):
      curr = heappop(heap)
      x, y = curr[1]
      if x + 1 < row:
        heappush(heap, (matrix[x + 1][y], (x + 1, y)))

    return heap[0][0]

  def kthSmallest1(self, matrix, k):
    import bisect
    lo, hi = matrix[0][0], matrix[-1][-1]
    while lo <= hi:
      mid = (lo + hi) >> 1
      loc = sum(bisect.bisect_right(m, mid) for m in matrix)
      if loc >= k:
        hi = mid - 1
      else:
        lo = mid + 1
    return lo

ins = KthSmallestElementInASortedMatrix()
matrix = [
  [ 1,  5,  9],
  [10, 11, 13],
  [12, 13, 15]
]
print(ins.kthSmallest(matrix, 8))
