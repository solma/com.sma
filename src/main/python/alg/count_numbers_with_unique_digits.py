# coding: utf-8
"""
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100,
excluding [11,22,33,44,55,66,77,88,99])
"""

from alg.label import Label

Label(Label.Math, Label.LeetCode)

class CountNumbersWithUniqueDigits(object):
  def countNumbersWithUniqueDigits(self, n):
    nums = [9]
    for x in range(9, 0, -1):
      nums.append(nums[-1] * x)
    return sum(nums[:n]) + 1 # +1 to include zero

ins = CountNumbersWithUniqueDigits()
assert ins.countNumbersWithUniqueDigits(2) == 9 + 81 + 1
assert ins.countNumbersWithUniqueDigits(3) == 9 + 81 + 81*8 + 1

