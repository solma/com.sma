from unittest import TestCase

from alg.create_maximum_number import CreateMaxNumber

class CreateMaxNumberTest(TestCase):
  def test_maxNumber(self):
    ins = CreateMaxNumber()
    cases = [
      ([3, 4, 6, 5], [9, 1, 2, 5, 8, 3], 5, [9, 8, 6, 5, 3]),
      ([6, 7], [6, 0, 4], 5, [6, 7, 6, 0, 4]),
      ([3, 9], [8, 9], 3, [9, 8, 9]),
    ]
    for case in cases:
      self.assertEqual(case[-1], ins.maxNumber(case[0], case[1], case[2]))
