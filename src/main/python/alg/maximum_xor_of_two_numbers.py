"""
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
Follow up: Could you do this in O(n) runtime?

Example 1:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:

Input: nums = [0]
Output: 0
Example 3:

Input: nums = [2,4]
Output: 6
Example 4:

Input: nums = [8,10,2]
Output: 10
Example 5:

Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127


Constraints:

1 <= nums.length <= 2 * 104
0 <= nums[i] <= 231 - 1
"""

from alg.data_structure import TrieNode
from alg.label import Label

Label(Label.BitManipulation, Label.Trie, Label.LeetCode)


class Solution:

  def findMaximumXOR(self, nums):
    r = 0
    l = [[nums, nums]]
    for i in range(31, -1, -1):
      count = 0
      newL = []
      for pair in l:
        zz, zo = [], []
        for zero in pair[0]:
          if (zero >> i) & 1 == 0:
            zz.append(zero)
          else:
            zo.append(zero)
        oz, oo = [], []
        for one in pair[1]:
          if (one >> i) & 1 == 0:
            oz.append(one)
          else:
            oo.append(one)
        if len(zz) > 0 and len(oo) > 0:
          newL.append([zz, oo])
          count += 1
        if len(zo) > 0 and len(oz) > 0:
          newL.append([zo, oz])
          count += 1
      if count > 0:
        l = newL
        r += 1 << i
    return r

  def findMaximumXORTrie(self, nums):

    def toBinStr(num):
      return bin(num)[2:].zfill(32)

    def buildTrie(nums):
      root = TrieNode()
      for num in nums:
        num_bin = toBinStr(num)
        cur = root
        for b in num_bin:
          if b not in cur.children:
            cur.children[b] = TrieNode()
          cur = cur.children[b]
      return root

    def findMaximumXorForOne(trie, num):
      num_bin = toBinStr(num)
      cur = trie
      other = ''
      for b in num_bin:
        rb = str(int(b) ^ 1)
        if rb in cur.children:
          branch = rb
        else:
          branch = b
        other += branch
        cur = cur.children[branch]
      return int('0b' + other, 2) ^ num

    if len(nums) == 1:
      return 0

    trie = buildTrie(nums)
    ret = 0
    for num in nums:
      ret = max(ret, findMaximumXorForOne(trie, num))
    return ret


ins = Solution()
for arr, ret in [
  ([2, 4], 6),
  ([3, 10, 5, 25, 2, 8], 28),
  ([0], 0),
  ([8, 10, 2], 10),
]:
  ans1, ans2 = ins.findMaximumXOR(arr), ins.findMaximumXORTrie(arr)
  if ans2 != ret or ans1 != ans2:
    print(arr, ans1, ans2)
