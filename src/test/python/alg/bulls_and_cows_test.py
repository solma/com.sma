from unittest import TestCase

from src.main.python.alg.bulls_and_cows import BullsAndCows

class TestBullsAndCows(TestCase):
  def test_getHint(self):
    ins = BullsAndCows()
    cases = {
      ('11', '10'): '1A0B',
      ('11', '01'): '1A0B',
      ('1234', '0111'): '0A1B'
    }
    for case, ans in cases.iteritems():
      self.assertEqual(ans, ins.getHint(case[0], case[1]))
