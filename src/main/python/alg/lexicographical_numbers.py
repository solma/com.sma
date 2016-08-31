"""
Given an integer n, return 1 - n in lexicographical order.
For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
Please optimize your algorithm to use less time and space.
The input size may be as large as 5,000,000.
"""

class LexicographicalNumbers(object):
  # TLE
  def lexicalOrder(self, n):

    def lexicalOrder_recur(i):
      if i > n:
        return
      ret.append(i)
      for j in range(10):
        lexicalOrder_recur(i * 10 + j)

    ret = []
    for i in range(1, 10):
      lexicalOrder_recur(i)
    return ret

ins = LexicographicalNumbers()
print ins.lexicalOrder(113)
