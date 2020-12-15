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
Label(Label.Array, Label.Greedy, Label.Subarray, Label.LeetCode, Label.LinearTime)

class IncreasingTripletSubsequence(object):
  def increasingTriplet(self, nums):
    small = middle = small_of_middle = float('inf')
    for n in nums:
      if n <= small:
        small = n
      elif n <= middle:
        middle = n
        small_of_middle = small
      else:
        return [small_of_middle, middle, n]
    return False

  # return only true/false
  # 2314
  def increasingTriplet_binary(self, nums):
    small = middle = float('inf')
    for n in nums:
      if n <= small:
        small = n
      elif n <= middle:
        middle = n
      else:
        return True
    return False

from unittest import TestCase
from utils.random_helper import gen_rand_array
class IncreasingTripletSubsequenceTest(TestCase):
  def test_increasingTriplet(self):
    ins = IncreasingTripletSubsequence()
    for n in range(1000):
      li = gen_rand_array(8, 100, True, True)
      res = ins.increasingTriplet(li)
      if res is False:
        assert ins.increasingTriplet_binary(li) is False
      else:
        if not res[0] < res[1] < res[2]:
          print(res)
          break
