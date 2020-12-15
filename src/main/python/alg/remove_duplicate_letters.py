"""
Given a string which contains only lowercase letters, remove duplicate letters so that
every letter appear once and only once. You must make sure your result is the smallest
in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
"""
from alg.label import Label
import collections

Label(Label.String, Label.Greedy, Label.Stack, Label.LeetCode)

class RemoveDuplicateLetters(object):

  def removeDuplicateLetters(self, s):
    return self.removeDuplicateLettersGreedy(s)

  def removeDuplicateLettersGreedy(self, s):
    ans = ''
    for _ in range(len(set(s))):
      top, idx = s[0], 0
      counter = collections.Counter(s)
      for c in range(len(s)):
        if top > s[c]:
          top, idx = s[c], c
        if counter[s[c]] == 1:
          break
        counter[s[c]] -= 1
      ans += top
      s = s[idx+1:].replace(top,'')
    return ans

  def removeDuplicateLettersStack(self, s):
    counter = collections.Counter(s)
    resultSet = set()
    stack = []
    for c in s:
      counter[c] -= 1
      if c in resultSet:
        continue
      while stack and stack[-1] > c and counter[stack[-1]]:
        resultSet.remove(stack.pop())
      resultSet.add(c)
      stack.append(c)
    return ''.join(stack)

  def removeDuplicateLettersRecursion(self, s):
    for c in sorted(set(s)):
      suffix = s[s.index(c):]
      if set(suffix) == set(s):
        return c + self.removeDuplicateLettersRecursion(suffix.replace(c, ''))
    return ''

from unittest import TestCase
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
