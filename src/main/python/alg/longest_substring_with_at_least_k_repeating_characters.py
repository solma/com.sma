"""
Find the length of the longest substring T of a given string
(consists of lowercase letters only) such that every character in T
appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3
The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:
s = "ababbc", k = 2
Output:
5
The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is
repeated 3 times.
"""
from alg.label import Label
Label(Label.Recursion, Label.DivideConquer, Label.LeetCode)

from collections import Counter
import re

class LongestSubstringWithAtLeastKRepeatingCharacters(object):
  def longestSubstring(self, s, k):
    cnt = Counter(s)
    filters = [x for x in cnt if cnt[x] < k]
    if not filters: return len(s)
    tokens = re.split('|'.join(filters), s)
    # print tokens
    return max(self.longestSubstring(t, k) for t in tokens)

ins = LongestSubstringWithAtLeastKRepeatingCharacters()
print(ins.longestSubstring('abadbfcaebbc', 2))
