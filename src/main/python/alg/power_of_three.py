"""
Given an integer < 2^32, write a function to determine if it is a power of three.
"""
from src.main.python.alg.label import Label

Label(Label.Math, Label.LeetCode)

class PowerOfThree(object):
  def isPowerOfThree(self, n):
    return n > 0 and 3 ** 20 % n == 0

    # alternative solution
    # import math as m
    # return n > 0 and n == 3 ** round(m.log(n) / m.log(3))

