"""
Additive number is a positive integer whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent
number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string represents an integer, write a function to determine if it's an additive number.
"""

from alg.label import Label
Label(Label.Recursion, Label.Backtracking, Label.LeetCode)

class Solution(object):
  def isAdditiveNumber(self, num):
    n = len(num)
    for j in range(1, n - 1):
      for k in range(j + 1, n):
        if self.is_additive_number_recur(num, n, 0, j, k):
          return True
    return False

  def is_additive_number_recur(self, num, n, i, j, k):
    if k >= n:
      return False
    res_len = max(j - i, k - j)
    for l in range(res_len, res_len + 2):
      #print(i, j, k, l, n)
      if (j > i + 1 and num[i] == '0') or (k > j + 1 and num[j] == '0') or (l > 1 and num[k] == '0'):
        return False
      if int(num[i:j]) + int(num[j:k]) == int(num[k:k + l]):
        if k + l == n:
          return True
        else:
          return self.is_additive_number_recur(num, n, j, k, k + l)
    return False
