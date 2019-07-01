"""
Given a string, find the longest substring with k distinct characters.
"""
from alg.label import Label
Label(Label.Hash, Label.Interview, Label.SlidingWindow, Label.Substring)
from utils import random_helper
from collections import Counter
import random as r


class LongestSubstringWithKDistinctCharacters:
  # O(n)
  def longest_substring_with_k_distinct_characters(self, str, K):
    m = dict()
    l = 0
    ret = ''
    for i, c in enumerate(str):
      if c not in m:
        m[c] = 0
      m[c] += 1
      if len(m) == K:
        r = i + 1
        substring = str[l:r]
        if len(substring) > len(ret):
          ret = substring
      elif len(m) == K + 1:
        # slide left side of the window
        while len(m) == K + 1:
          lc = str[l]
          m[lc] -= 1
          if m[lc] == 0:
            del m[lc]
          l += 1

    if not ret:
      raise Exception('%s does not contain %d distinct characters' % (str, K))
    return ret

  def brute_force(self, str, K):
    n = len(str)
    ret = ''
    for i in range(n):
      for j in range(i + 1, n + 1):
        substring = str[i:j]
        c = Counter(substring)
        if len(c.keys()) == K:
          if j - i > len(ret):
            ret = substring
    return ret

ins = LongestSubstringWithKDistinctCharacters()
cases = [
  # ('aaabcd', 10),
  ('bbacc', 3),
  ('aabbcc', 3),
  ('abcabbabccccdddaaadadaoiwuertlknbkjhggggggggggggggggh', 5),
  ('ababcbcbaaabbdef', 3),
  ('aabacbebebe', 3),
]
cases = [
  (random_helper.gen_rand_str(20), r.randint(2, 6)) for _ in range(100)
]
for case in cases:
  try:
    res1 = ins.longest_substring_with_k_distinct_characters(case[0], case[1])
    ground_truth = ins.brute_force(case[0], case[1])
    if res1 != ground_truth:
      print(case, res1, ground_truth)
  except Exception as e:
    print(e)
