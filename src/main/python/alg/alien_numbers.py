'''
example input :
4
9 0123456789 oF8
Foo oF8 0123456789
13 0123456789abcdef 01
'''

import sys

N = int(sys.stdin.readline().rstrip('\n'))

for case in range(N):
  word, s_lan_alphabet, d_lan_alphabet = sys.stdin.readline().strip('\n').split()
  s_lan_base = {}
  for i in range(len(s_lan_alphabet)):
    s_lan_base[s_lan_alphabet[i]] = i

  word_in_decimal = 0
  base = len(s_lan_alphabet)
  for i in range(len(word)):
    word_in_decimal += s_lan_base[word[i]] * base ** (len(word) - i - 1)

  l = 0
  base = len(d_lan_alphabet)
  while base ** l <= word_in_decimal:
    l += 1
  word_trans = []

  while word_in_decimal:
    l -= 1
    c = int(word_in_decimal / base ** l)
    word_trans.append(d_lan_alphabet[c])
    word_in_decimal -= c * base ** l
  word_trans.extend([d_lan_alphabet[0]] * l)
  print
  "Case #%d: %s" % (case + 1, ''.join(word_trans))
