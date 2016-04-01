from unittest import TestCase

from alg.super_ugly_numbers import SuperUglyNumbers

class TestSuperUglyNumbers(TestCase):
  def testNthSuperUglyNumber(self):
    ins = SuperUglyNumbers()
    cases = [
      (12, [2, 7, 13, 19], 32)
    ]
    for case in cases:
      self.assertEquals(case[2], ins.nthSuperUglyNumber(case[0], case[1]))
