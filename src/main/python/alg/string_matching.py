# calculate the next array in KMP/sunday algorithm
def calNext(t, m):
  if m == "kmp":
    next = [0] * len(t)
    next[0] = -1
    i = 0
    j = -1
    while i < len(t) - 1:
      print i, t[i], j, t[j], t[:i + 1], next[:i + 1]
      if j == -1 or t[i] == t[j]:
        i += 1
        j += 1
        next[i] = j
      else:
        j = next[j]
  else:  # sunday
    next = [len(t)] * 26
    for i in range(len(t)):
      next[ord(t[i]) - ord('a')] = len(t) - i

  return next


def match(s, t, m):
  # preprocessing before match
  if m == "kmp" or m == "sunday": next = calNext(t, m)

  i = j = 0
  while i + j < len(s):
    if s[i + j] == t[j]:
      if j == len(t) - 1:
        print s[0:i], s[i:i + len(t)], s[i + len(t):]
        return i
      j += 1
    else:
      if m == "kmp":  # kmp
        i += j - next[j]
        j = max(next[j], 0)
      else:
        if m == "bf":  # brutal force
          i += 1  # when t does not have a "pattern", i can increase more aggressively, i.e. max(1,j)
          j = 0
        else:  # sunday
          i += next[ord(s[min(i + len(t), len(s) - 1)]) - ord('a')]
          j = 0

  print
  s + " failed to match " + t
  return -1


# print next('ababc')
# match('abbba', 'bba', 'bf')
mode = "sunday"
match('abbcbba', 'bba', mode)
# print calNext('participate in parachute')
# print calNext('ababcababababcabab+',mode)
