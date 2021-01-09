"""
Given a sequence of n integers a1, a2, ..., an,
a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm
that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
"""

from alg.label import *

Label(Label.Array, Label.Stack, Label.Subarray, Label.LeetCode, Label.LinearTime)

class Pattern132(object):
  def find132pattern(self, nums):
    e3, st = float('-inf'), []
    for e in reversed(nums):
      if e < e3:
        return True
      while st and e > st[-1]:
        e3 = st.pop()
      st.append(e)
    return False


ins = Pattern132()
for arr in [[1, 2, 3, 4], [3, 1, 4, 2], [-1, 3, 2, 0],
            [1, 0, 1, -4, -3]]:
  print(ins.find132pattern(arr))
