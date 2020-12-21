"""
A sequence of numbers is called arithmetic if it consists of at least three elements and
if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of
integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of the array A is called arithmetic if the sequence:
A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
"""

from alg.label import Label
Label(Label.Array, Label.Subarray, Label.LinearTime, Label.LeetCode)

class Solution(object):
  def numberOfArithmeticSlices(self, A):
    """
    :type A: List[int]
    :rtype: int
    """
    n = len(A)
    if n < 3:
      return 0
    d, prev, strike, ret = None, A[0], 0, 0
    for c in A[1:]:
      nd = c - prev
      if nd == d:
        strike += 1
        if strike >= 3:
          ret += strike - 2
      else:
        strike = 2
        d = nd
      prev = c
    return ret

ins = Solution()
for case in [
  [1, 2, 4],
  [1, 2, 3, 4],
  [1, 1, 1, 1, 1],
  [1, 2, 3, 5, 7, 8, 9],
  [1, 2, 3, 5, 7, 9],
]:
  print(ins.numberOfArithmeticSlices(case))
