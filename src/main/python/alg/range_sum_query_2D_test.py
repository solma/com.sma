from unittest import TestCase
from alg.range_sum_query_2D import NumMatrix


class NumMatrixTest(TestCase):
  def test_sumRegion(self):
    ins = NumMatrix([
      [3, 0, 1, 4, 2],
      [5, 6, 3, 2, 1],
      [1, 2, 0, 1, 5],
      [4, 1, 0, 1, 7],
      [1, 0, 3, 0, 5]
    ])
    self.assertEqual(8, ins.sumRegion(2, 1, 4, 3))
    self.assertEqual(11, ins.sumRegion(1, 1, 2, 2))
    self.assertEqual(12, ins.sumRegion(1, 2, 2, 4))
