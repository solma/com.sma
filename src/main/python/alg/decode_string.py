"""
Given an encoded string, return it's decoded string.
The encoding rule is: k[encoded_string],
where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid;
No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and
that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
"""

from alg.label import Label
Label(Label.Stack, Label.ConstantTime, Label.LeetCode)

class DecodeString(object):
  def decodeString(self, s):

    def pop():
      poped = []
      while string_stck and string_stck[-1] != '[':
        poped.insert(0, string_stck.pop())
      if string_stck and string_stck[-1] == '[':
        string_stck.pop()
      return num_stck.pop() * ''.join(poped)

    ret = ''
    num_stck, string_stck = [1], []
    i = 0
    while i < len(s):
      c = s[i]
      if '0' <= c <= '9':
        j = i + 1
        while j < len(s) and '0' <= s[j] <= '9':
          j += 1
        num_stck.append(int(s[i : j]))
        i = j
        continue
      elif 'a' <= c <= 'z' or c == '[':
        string_stck.append(c)
      elif c == ']':
        string_stck.append(pop())
      i += 1

    if string_stck:
      ret += pop()
    return ret

ins = DecodeString()
for s in ['10[a]2[bc]', '3[a11[c]]', '2[abc]3[cd]ef']:
  print '%s: %s' % (s, ins.decodeString(s))
