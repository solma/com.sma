"""
Given a string, find the first non-repeating character in it and return it's index.
If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
"""
from label import Label
Label(Label.Hash, Label.LinearSpace, Label.LeetCode)

class FirstUniqueChar(object):
  def firstUniqChar(self, s):
    from collections import Counter
    c = Counter(s)
    for i in range(len(s)):
      if c[s[i]] == 1:
        return i
    return -1

ins = FirstUniqueChar()
print(ins.firstUniqChar('leetcode'))
print(ins.firstUniqChar('loveleetcode'))
print(ins.firstUniqChar('aabb'))

