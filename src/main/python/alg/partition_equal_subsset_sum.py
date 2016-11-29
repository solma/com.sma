"""
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Both the array size and each of the array element will not exceed 100.

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
"""
from alg.label import Label
Label(Label.Recursion, Label.DynamicProgramming, Label.LeetCode)

class PartitionEqualSubsetSum(object):
  # TLE
  def canPartition(self, nums):
    n = sum(nums)
    if n & 1 == 1:
      return False

    q = []
    q.append((nums, n >> 1))
    while q:
      arr, summation = q.pop()
      if summation == 0:
        return True
      if len(arr) < 1 or summation < 0:
        continue
      q.append((arr[1:], summation))
      q.append((arr[1:], summation - arr[0]))
      # print q
    return False

  def canPartition1(self, nums):
    sums = sum(nums)
    if sums & 1: return False
    nset = {0}
    for n in nums:
      # print 'n: %d' % n
      for m in nset.copy():
        nset.add(m + n)
    # print nset
    return sums / 2 in nset

ins = PartitionEqualSubsetSum()
for array in [
  [1, 5, 11, 5, 6],
  [1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100,99,100]
][1:]:
  print ins.canPartition1(array)
