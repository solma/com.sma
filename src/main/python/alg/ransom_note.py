"""
Given an arbitrary ransom note string and another string containing letters from
all the magazines, write a function that will return true if the ransom note can
be constructed from the magazines; otherwise, it will return false.
Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
"""

from alg.label import Label
Label(Label.Hash, Label.LinearTime, Label.LeetCode)

class RansomNote(object):
  def canConstruct(self, ransomNote, magazine):
    """
    :type ransomNote: str
    :type magazine: str
    :rtype: bool
    """
    import collections
    ransom_counter = collections.Counter(ransomNote)
    magazine_counter = collections.Counter(magazine)
    if set(ransom_counter) > set(magazine_counter):
      return False

    for c in set(ransom_counter):
      if ransom_counter[c] > magazine_counter[c]:
        return False
    return True

ins = RansomNote()
print ins.canConstruct("a", "b")
print ins.canConstruct("aa", "ab")
print ins.canConstruct("aa", "aab")

