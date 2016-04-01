from src.main.python.alg.label import Label

Label(Label.Hash, Label.SlidingWindow, Label.TwoOrMorePointers)

from collections import OrderedDict
from collections import defaultdict

class LongestSubstringWithAtMostKDistinctCharactersInStream:
  # O(n)
  def longest_substring_with_at_most_k_distinct_characters_in_stream(self, stream, K):
    l = r = 0
    d = OrderedDict()
    ret = ''
    for c in stream:
      if c in d:
        del d[c]
      d[c] = r
      r += 1
      if len(d) == K + 1:
        k, v = d.items()[0]
        l = v + 1
        del d[k]
      if r - l > len(ret):
        ret = stream[l:r]
    return ret

  # O(n^2)
  def longest_substring_with_at_most_k_distinct_characters_in_stream_1(self, stream, K):
    l = r = 0
    d = defaultdict(int)
    ret = ''
    for c in stream:
      d[c] += 1
      r += 1
      while len(d) == K + 1:
        k = stream[l]
        d[k] -= 1
        if d[k] == 0:
          del d[k]
        l += 1
      if r - l > len(ret):
        ret = stream[l:r]
    return ret

ins = LongestSubstringWithAtMostKDistinctCharactersInStream()
cases = [('aaabcd', 10),
         ('bbacc', 2),
         ('abcabbabccccdddaaadadaoiwuertlknbkjhggggggggggggggggh', 2),
         ('ababcbcbaaabbdef', 2)
         ]
for case in cases:
  res1 = ins.longest_substring_with_at_most_k_distinct_characters_in_stream(case[0], case[1])
  res2 = ins.longest_substring_with_at_most_k_distinct_characters_in_stream_1(case[0], case[1])
  assert res1 == res2
