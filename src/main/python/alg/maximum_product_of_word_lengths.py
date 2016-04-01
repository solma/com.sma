"""
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not
share common letters. You may assume that each word will contain only lower case letters. If no such two words exist,
return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
"""
import collections
import string

from alg.label import Label

Label(Label.Hash, Label.LeetCode)


class MaximumProductOfWordLengths(object):
  def maxProduct(self, words):
    es = [set() for _ in range(26)]
    ml = collections.defaultdict(int)
    for w in words:
      hash = sum(1 << (ord(x) - ord('a')) for x in set(w))
      ml[hash] = max(ml[hash], len(w))
      for x in set(string.lowercase) - set(w):
        es[ord(x) - ord('a')].add(hash)

    ans = 0
    for w in words:
      r = [es[ord(x) - ord('a')] for x in w]
      if not r: continue
      r = set.intersection(*r)
      for x in r:
        ans = max(ans, len(w) * ml[x])
    return ans
