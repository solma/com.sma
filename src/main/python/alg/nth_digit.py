"""
Find the nth digit of the infinite integer sequence
1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:
Input:
3
Output:
3

Example 2:
Input:
11
Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0,
which is part of the number 10.
"""
from alg.label import *
Label(Label.Math, Label.LeetCode)

class NthDigit(object):
  def findNthDigit(self, n):
    # build the milestone list
    li = []
    sum, i = 0, 0
    while sum < 2 ** 32:
      sum += 9 * 10 ** i * (i + 1)
      i += 1
      li.append(sum)

    import bisect, math
    idx = bisect.bisect_left(li, n)
    reminder = n - (li[idx - 1] if idx > 0 else 0)
    if reminder == 0:
      return str(10 ** idx - 1)[-1]
    else:
      num = int((math.ceil(reminder / (idx + 1.0)) - 1)) + 10 ** idx
      print(idx, li, num)
      return int(str(num)[reminder % (idx + 1) - 1])

ins = NthDigit()
for x in [1, 3, 10, 11, 20, 100, 10000]:
  print(ins.findNthDigit(x))


