"""
Given a string, find the longest substring with at most k distinct characters.
"""
from alg.label import *

Label(Label.Hash, Label.Interview, Label.SlidingWindow, Label.Substring, Label.TwoPointers)

import collections


class LongestSubstringWithAtMostKDistinctCharacters:
  # O(n)
  def longest_substring_with_at_most_k_distinct_characters(self, s, K):
    l = r = 0
    # keep track of insertion order
    d = collections.OrderedDict()
    ret = ''
    for c in s:
      if c in d:
        del d[c]
      d[c] = r
      r += 1
      if len(d) == K + 1:
        k, v = list(d.items())[0]
        l = v + 1
        del d[k]
      if r - l > len(ret):
        ret = s[l:r]
    return ret

  # O(n^2)
  def longest_substring_with_at_most_k_distinct_characters_1(self, s, K):
    l = r = 0
    d = collections.defaultdict(int)
    ret = ''
    for c in s:
      d[c] += 1
      r += 1
      while len(d) == K + 1:
        k = s[l]
        d[k] -= 1
        if d[k] == 0:
          del d[k]
        l += 1
      if r - l > len(ret):
        ret = s[l:r]
    return ret


verify_solution(
  LongestSubstringWithAtMostKDistinctCharacters(),
  "longest_substring_with_at_most_k_distinct_characters",
  [
    ('aaabcd', 10, 'aaabcd'),
    ('bbacc', 2, 'bba'),
    ('abcabbabccccdddaaadadaoiwuertlknbkjhggggggggggggggggh', 2, 'hggggggggggggggggh'),
    ('ababcbcbaaabbdef', 2, 'baaabb')
  ])
