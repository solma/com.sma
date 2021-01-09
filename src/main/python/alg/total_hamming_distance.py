"""
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
"""

from alg.label import *
Label(Label.BitManipulation, Label.LeetCode, Label.LinearTime)

class TotalHammingDistance(object):
  def totalHammingDistance(self, nums):
    cnt = 0
    for i in range(32):
      zeros, ones = 0, 0
      for n in nums:
        bit = (n >> i) & 1
        if bit == 1:
          ones += 1
        else:
          zeros += 1
        cnt += ones * zeros
    return cnt

ins = TotalHammingDistance()
print(ins.totalHammingDistance([4, 14, 2]))
