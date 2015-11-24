from unittest import TestCase

from src.main.python.alg.additive_number import Solution

class TestSolution(TestCase):
  def test_isAdditiveNumber(self):
    ins = Solution()
    self.assertTrue(ins.isAdditiveNumber('101'))
    self.assertTrue(ins.isAdditiveNumber('112'))
    self.assertTrue(ins.isAdditiveNumber('112358'))
    self.assertTrue(ins.isAdditiveNumber('199100199'))
    self.assertFalse(ins.isAdditiveNumber('19910019'))
    self.assertFalse(ins.isAdditiveNumber('113'))
    self.assertFalse(ins.isAdditiveNumber('1023'))
    self.assertFalse(ins.isAdditiveNumber('1203'))
