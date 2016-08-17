"""
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result.
Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
"""

from alg.label import Label
Label(Label.Array, Label.RandomT, Label.LinearTime, Label.LeetCode)

class ShuffleAnArray(object):

  def __init__(self, nums):
    self._copy = list(nums)
    self._array = nums

  def reset(self):
    self._array = list(self._copy)
    return self._array

  def shuffle(self):
    import random as rand
    n = len(self._array)
    for i in range(n):
      j = rand.randint(0, i)
      self._array[i], self._array[j] = self._array[j], self._array[i]
    return self._array


# Your Solution object will be instantiated and called as such:
obj = ShuffleAnArray([1, 2, 3, 4, 5, 6, 7])
print obj.shuffle()
print obj.reset()
print obj.shuffle()