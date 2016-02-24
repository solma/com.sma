"""
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 <= i < j < k <= n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
"""

from src.main.python.alg.label import Label

Label(Label.Array, Label.Greedy, Label.LeetCode)

class IncreasingTripletSubsequence(object):
  def increasingTriplet(self, nums):
    small = middle = None
    for n in nums:
      if small is None or n <= small:
        small = n
      elif middle is None or n <= middle:
        middle = n
      else:
        return True
    return False
