"""
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.
"""

from alg.label import *
Label(Label.Arithmetic, Label.BinarySearch, Label.LeetCode)

class ValidPerfectSquare(object):
  def isPerfectSquare(self, num):
    l, r = 0, num
    while l + 1 < r:
      m = (l + r) / 2
      square = m ** 2
      if square == num:
        return True
      if square > num:
        r = m
      else:
        l = m
    return r ** 2 == num

ins = ValidPerfectSquare()
for i in range(1, 20):
  print(i, ins.isPerfectSquare(i))

