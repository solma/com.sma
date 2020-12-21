"""
A password is considered strong if the below conditions are all met:

It has at least 6 characters and at most 20 characters.
It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
It does not contain three repeating characters in a row (i.e., "...aaa..." is weak, but "...aa...a..." is strong,
assuming other conditions are met).
Given a string password, return the minimum number of steps required to make password strong.
if password is already strong, return 0.

In one step, you can:

Insert one character to password,
Delete one character from password, or
Replace one character of password with another character.


Example 1:

Input: password = "a"
Output: 5
Example 2:

Input: password = "aA1"
Output: 3
Example 3:

Input: password = "1337C0d3"
Output: 0
"""

from alg.label import Label

Label(Label.BFS, Label.Backtracking, Label.LeetCode)


class Solution(object):
  lowercase = set('abcdefghijklmnopqrstuvwxyz')
  uppercase = set('ABCDEFGHIJKLMNOPQRSTUFVWXYZ')
  digit = set('0123456789')

  def strongPasswordChecker(self, s):
    """
    :type password: str
    :rtype: int

    Missing/length reqs are easy by themselves.
    For repeating sequences, we need to find number of ADDITIONAL replaces.
    We can insert (ideal for len(s) < 6) to disrupt missing (+ reduce one replace).
    We can delete (ideal for len(s) > 20) 1-3 characters to reduce one replace.
    We can replace as last resort.

    Excellent explainer: https://leetcode.com/problems/strong-password-checker/discuss/478197/Explanation-of-How-To-Approach-This-Problem
    """
    # Requirement 2
    req_missing = 3
    req_missing -= any(c.islower() for c in s)
    req_missing -= any(c.isupper() for c in s)
    req_missing -= any(c.isdigit() for c in s)

    # Requirement 3
    repeat_replace = 0
    mod0 = mod1 = 0
    i = 2
    while i < len(s):
      if s[i] == s[i - 1] == s[i - 2]:  # start of repeating sequence
        curr = 2  # track length of current repeating sequence
        while i < len(s) and s[i - 1] == s[i]:
          curr += 1
          i += 1
        repeat_replace += curr // 3  # {# of raw replacements needed}
        # We can reduce replaces, by using strategic deletes
        mod0 += int(curr % 3 == 0)  # mod0 -> mod2 costs 1 delete to save one replace
        mod1 += int(curr % 3 == 1)  # mod1 -> mod2 costs 2 deletes to save one replace
        # mod2 -> mod2 costs 3 deletes to save one replace
      else:
        i += 1

    # Requirement 1
    res = max(0, len(s) - 20)  # {# deletes} + {# inserts} + {# replaces}
    if len(s) > 20:
      deletes = len(s) - 20  # need to delete, then replace
      repeat_replace -= min(deletes, mod0)  # delete mod0 -> mod2 == save one replace
      deletes = max(0, deletes - mod0)
      repeat_replace -= min(deletes, mod1 * 2) // 2  # delete mod1 -> mod2 == save one replace
      deletes = max(0, deletes - mod1 * 2)
      repeat_replace -= deletes // 3  # use remaining deletes to mod2 -> mod2
      # can insert or replace with missing chars, so they're interchangeable here
    res += max(6 - len(s), req_missing, repeat_replace)

    return res


ins = Solution()
for case in [
  'a',
  'aA1',
  '1337C0d3',
]:
  print(ins.strongPasswordChecker(case))
