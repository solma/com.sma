"""
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".
"""
from alg.label import Label
Label(Label.Array, Label.String, Label.LeetCode)

class ReverseVowelsOfAString(object):
  def reverseVowels(self, s):
    vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
    li = list(s)
    l, r = 0, len(li) - 1
    while l < r:
      while l < r and li[l] not in vowels:
        l += 1
      while l < r and li[r] not in vowels:
        r -= 1
      if l < r:
        li[l], li[r] = li[r], li[l]
        l += 1
        r -= 1
    return ''.join(li)
