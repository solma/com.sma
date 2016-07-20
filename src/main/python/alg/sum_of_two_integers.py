"""
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.
"""
from alg.label import Label
Label(Label.BitManipulation, Label.LeetCode)

class SumOfTwoIntegers(object):
  def getSum(self, a, b):
    MAX_INT = 0x7FFFFFFF
    MIN_INT = 0x80000000
    MASK = 0x100000000
    while b:
      a, b = (a ^ b) % MASK, ((a & b) << 1) % MASK
    return a if a <= MAX_INT else ~((a % MIN_INT) ^ MAX_INT)

ins = SumOfTwoIntegers()
for a, b in [(-2, 2), (1, -9), (-14, 16), (-2, -3)]:
  print a + b, ins.getSum(a, b)
