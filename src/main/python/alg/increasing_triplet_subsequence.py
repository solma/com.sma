"""
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
If there exists i, j, k such that arr[i] < arr[j] < arr[k] given 0 <= i < j < k <= n-1
then return true else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
one possible return [0, 1, 2].

Given [5, 4, 3, 2, 1],
return false.
"""

from alg.label import Label

Label(Label.Array, Label.Greedy, Label.LeetCode)

class IncreasingTripletSubsequence(object):
  def increasingTriplet(self, nums):
    small = small_idx = middle = middle_idx = old_small_idx = None
    for i in range(len(nums)):
      n = nums[i]
      if small is None or n <= small:
        small = n
        old_small_idx = small_idx
        small_idx = i
        small_idx_smaller_than_middle_idx = False
      elif middle is None or n <= middle:
        middle = n
        middle_idx = i
        small_idx_smaller_than_middle_idx = True
      else:
        return [small_idx if small_idx_smaller_than_middle_idx else old_small_idx, middle_idx, i]
    return False

  # return only true/false
  def increasingTriplet_binary(self, nums):
    small = middle = None
    for n in nums:
      if small is None or n <= small:
        small = n
      elif middle is None or n <= middle:
        middle = n
      else:
        return True
    return False
