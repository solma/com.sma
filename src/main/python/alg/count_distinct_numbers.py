"""
Count # of distinct numbers in a large array
"""
from alg.label import *
Label(Label.Hash, Label.Sampling, Label.LeetCode)

class CountDistinctNumbers(object):
  def countDistinctNumbers(self, li):
    N = len(li)

    def direct_hash():
      """
      direct hash
      """
      import mathproblems as m
      bm = [0] * int(m.ceil(N / 8.0))
      for r in li:
        bm[(r - 1) / 8] |= 0x1 << (r - 1) % 8
      cnt = 0
      # print bm
      for e in bm:
        for i in range(8):
          if e >> i & 0x1 == 1:
            cnt += 1
      print(cnt)

    def sampling():
      minimum = 1
      for r in li:
        minimum = min(minimum, r / (N - 1.0))
      print(1 / minimum - 1)

    def groundTruth():
      from collections import Counter
      c = Counter(li)
      print(len(c.keys()))

    groundTruth()
    direct_hash()
    sampling()

ins = CountDistinctNumbers()
from utils import random_helper
x = 1000000
ins.countDistinctNumbers(random_helper.gen_rand_array(l=x, max_num=x))


