from unittest import TestCase
from alg.remove_duplicate_letters import RemoveDuplicateLetters

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
      self.assertEquals(case[1], ins.removeDuplicateLettersGreedy(case[0]))
