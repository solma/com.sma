"""
You have a number of envelopes with widths and heights given as a pair of integers (w, h).
One envelope can fit into another if and only if both the width and height of one envelope is
greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes
you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
"""

from alg.label import *
Label(Label.Array, Label.DynamicProgramming, Label.LinearithmicTime, Label.LeetCode)

class RussianDollEnvelopes(object):
  def maxEnvelopes(self, envelopes):
    # sorted base on width then reversed height, so that dolls with the same width
    # will only contribute at most one height to the LIS.
    nums = sorted(envelopes,
                  cmp = lambda x, y: x[0] - y[0] if x[0] != y[0] else y[1] - x[1])
    size = len(nums)
    dp = []
    for x in range(size):
      # LIS: given x, replace first ele >= x in LIS with x.
      low, high = -1, len(dp)
      while low + 1 < high:
        mid = (low + high) / 2
        if dp[mid][1] < nums[x][1]:
          low = mid
        else:
          high = mid
      if high < len(dp):
        dp[high] = nums[x]
      else:
        dp.append(nums[x])
    # dp is not necessarily the correct answer
    return len(dp)

ins = RussianDollEnvelopes()
inputs = [
  [[4,5],[4,6],[6,7],[2,3],[1,1]],
  [[5,4],[6,4],[6,7],[2,3]],
]
for envelopes in inputs:
  print(envelopes, ins.maxEnvelopes(envelopes))

