from unittest import TestCase

from src.main.python.alg.range_sum_query_mutable import NumArray

class TestNumArray(TestCase):
  def test_sumRange(self):
    na = NumArray([-2, 0, 3, -5, 2, -1])
    self.assertEqual(1, na.sumRange(0, 2))
    self.assertEqual(-1, na.sumRange(2, 5))
    self.assertEqual(-3, na.sumRange(0, 5))
    na.update(0, 1)
    self.assertEqual(4, na.sumRange(0, 2))
    self.assertEqual(-1, na.sumRange(2, 5))
    self.assertEqual(0, na.sumRange(0, 5))
