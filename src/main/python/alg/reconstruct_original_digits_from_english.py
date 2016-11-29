"""
Given a non-empty string containing an out-of-order English representation of digits 0-9,
output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits.
That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"
Output: "012"

Example 2:
Input: "fviefuro"
Output: "45"

"""
from alg.label import Label
Label(Label.Greedy, Label.Recursion, Label.LeetCode)

from collections import Counter
import sys

class ReconstructOriginalDigitsFromEnglish(object):
  # top-down recursion
  def originalDigits1(self, s):
    # count how many counter2's can be included in counter1
    def includeMax(counter1, counter2):
      m = sys.maxint
      for c in counter2:
        ratio = counter1[c] / counter2[c]
        if ratio == 0:
          return 0
        else:
          m = min(m, ratio)
      return m

    def subtract(counter1, counter2, m):
      ret = Counter(counter1)
      for c in ret:
        ret[c] -= m * counter2[c]
      return ret

    def decode(cs, i):
      if cs and cs.most_common()[0][1] == 0:
        return ''
      if i == 10:
        return False
      m = includeMax(cs, digit_counters[i])
      for n in range(m, -1, -1):
        ncs = subtract(cs, digit_counters[i], n)
        # print n, i, ncs
        ret = decode(ncs, i + 1)
        if ret is not False:
          return str(i) * n + ret
      return False

    digit_counters = []
    for digit in ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']:
      digit_counters.append(Counter(digit))
    cache = {}
    return ''.join(sorted(decode(Counter(s), 0)))

  # bottom up
  def originalDigits(self, s):
    def get_key(counter):
      key = []
      for c in counter:
        key.append(str(c) + ':' + str(counter[c]))
      return ','.join(key)

    digit_counters = []
    for digit in ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']:
      digit_counters.append(Counter(digit))
    sc = Counter(s)

    empty = Counter()
    counters = {get_key(empty) : (empty, '')}

    while True:
      for counter_key in counters.copy():
        for i, dc in enumerate(digit_counters):
          nc = counters[counter_key][0] + dc
          key = get_key(nc)
          if nc > sc:
            continue
          counters[key] = (nc, counters[counter_key][1] + str(i))
          if nc == sc:
            return ''.join(sorted(counters[key][1]))
    raise RuntimeError


  """
  top-logical sorting: 6028745913
  Observe that six, zero, two, eight, seven, four is the only word contains x, z, w, g, v, u respectively
  so 6, 0, 2, 8, 7, 4 are ranked first.
  Among the 4 digits, five is the only word that include f, so it's ranked next
  So on and so on to get the list 6028745913
  """
  def originalDigitsTrick(self, s):
    sc = Counter(s)
    digit_words = ['six', 'zero', 'two', 'eight', 'seven', 'four', 'five', 'nine', 'one', 'three']
    digit_counters = [Counter(word) for word in digit_words]
    digits = [6, 0, 2, 8, 7, 4, 5, 9, 1, 3]
    ans = [0] * 10
    for idx, word in enumerate(digit_words):
      dc = digit_counters[idx]
      t = min(sc[c] / dc[c] for c in dc)
      ans[digits[idx]] = t
      for c in dc:
        sc[c] -= t * dc[c]
    return ''.join(str(i) * n for i, n in enumerate(ans))


ins = ReconstructOriginalDigitsFromEnglish()
for s in [
  'zeroonetwothreefourfivesixseveneightnine',
  'owoztneoer',
  'fviefuro',
  'fvowoztneoeriefuro',
  'zerozero'][:]:
  print ins.originalDigitsTrick(s)


