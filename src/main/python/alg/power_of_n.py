"""
Given an integer < 2^32, write a function to determine if it is a power of N.
"""
from alg.label import *

Label(Label.BitManipulation, Label.Math, Label.LeetCode)

class PowerOfN(object):
  def isPowerOfThree(self, n):
    # alternative solution
    # import math as m
    # return n > 0 and n == 3 ** round(m.log(n) / m.log(3))
    return n > 0 and 3 ** 20 % n == 0

  def isPowerOfFour(self, n):
    def isPowerOfTwo(n):
      return n > 0 and (n & (n - 1)) == 0
    return isPowerOfTwo(n) and (n & 0xAAAAAAAA) == 0



