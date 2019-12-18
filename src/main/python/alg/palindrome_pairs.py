"""
Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
"""

from alg.label import Label

Label(Label.DynamicProgramming, Label.CubicTime, Label.LeetCode)

class PalindromePairs(object):

  # O(l^2n), l=max(map(len, words)), n=len(words)
  def palindromePairs(self, words):
    def palindromePairsForOneWord(word, idx):
      n = len(word)
      pairs = []
      if n == 1:
        return pairs

      is_palindrome = [[False] * n for _ in range(n)]
      for size in range(1, n + 1):
        for l in range(0, n - size + 1):
          r = l + size - 1
          if word[l] == word[r] and (size <= 3 or is_palindrome[l + 1][r - 1]):
            is_palindrome[l][r] = True

      for i in range(0, n + 1):
        suffix_reverse = word[i:][::-1]
        if (i == 0 or is_palindrome[0][i - 1]) and suffix_reverse in d.keys():
          pairs.append([d[suffix_reverse], idx])
      return pairs

    d = {y : x for x, y in enumerate(words)}
    pairs = []
    for word in words:
      pairs += palindromePairsForOneWord(word, d[word])
    return pairs

ins = PalindromePairs()
print(ins.palindromePairs(["abcd", "dcba", "lls", "s", "sssll"]))
