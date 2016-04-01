"""
Remove the minimum number of invalid parentheses in order to
make the input string valid. Return all possible results.
Note: The input string may contain letters other than the parentheses '(' and ')'.

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
"""

from alg.label import Label

Label(Label.BFS, Label.DFS, Label.LeetCode)


class Solution(object):
  def removeInvalidParentheses(self, s):
    def _remove_invalid_parentheses(s, left_right_diff, st, cur):
      if left_right_diff < 0:
        return
      if s == '':
        if left_right_diff == 0:
          st.add(cur)
        return
      c = s[0]
      if c not in ['(', ')']:
        _remove_invalid_parentheses(s[1:], left_right_diff, st, cur + c)
      else:
        if c is '(':
          _remove_invalid_parentheses(s[1:], left_right_diff + 1, st, cur + c)
        else:
          _remove_invalid_parentheses(s[1:], left_right_diff - 1, st, cur + c)
          _remove_invalid_parentheses(s[1:], left_right_diff, st, cur)

    st = set()
    _remove_invalid_parentheses(s, 0, st, '')
    if len(st) == 0:
      return ['']
    return list(st)

# print Solution().removeInvalidParentheses("()(k)o)()()")
