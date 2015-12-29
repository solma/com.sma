from unittest import TestCase
from src.main.python.alg.remove_duplicate_letters import RemoveDuplicateLetters

class TestRemoveDuplicateLetters(TestCase):
  def test_removeDuplicateLetters(self):
    cases = [
      ('cabc', 'abc'),
      ('ccbbaa', 'cba'),
      ('cbacdcbc', 'acdb'),
      ('bcabc', 'abc'),
      ('ccacbaba', 'acb'),
      ('abacb', 'abc')
    ]
    ins = RemoveDuplicateLetters()
    for case in cases:
      self.assertEquals(case[1], ins.removeDuplicateLetters(case[0]))
