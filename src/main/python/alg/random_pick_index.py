"""
Given an array of integers with possible duplicates,
randomly output the index of a given target number.
You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space
will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly.
Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
"""
from alg.label import *
Label(Label.ReservoirSampling, Label.Hash, Label.LinearTime, Label.LinearSpace, Label.LeetCode)

from collections import defaultdict
import random as rand

class RandomPickIndex(object):

  class LinearSpaceSolution(object):
    def __init__(self, nums):
      self._idx = defaultdict(list)
      for i, n in enumerate(nums):
        self._idx[n].append(i)

    def pick(self, target):
      return self._idx[target][rand.randint(0, len(self._idx[target]) - 1)]

  class LinearTimeSolution(object):
    def __init__(self, nums):
      from collections import Counter
      self._nums = nums
      self._c = Counter(nums)

    def pick(self, target):
      ret, cnt = None, 0
      for i, n in enumerate(self._nums):
        if n != target:
          continue
        if ret is None or rand.randint(0, cnt) == 0:
          ret = i
        cnt += 1
        if cnt == self._c[n]:
          break
      return ret

ins = RandomPickIndex.LinearSpaceSolution([1, 2, 3, 2, 1, 3, 2])
c = defaultdict(int)
for i in range(1000000):
  c[ins.pick(2)] += 1
print(c)
