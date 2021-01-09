"""
A frog is crossing a river.
The river is divided into x units and at each unit there may or may not exist a stone.
The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order,
determine if the frog is able to cross the river by landing on the last stone.
Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either
k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is >= 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping
1 unit to the 2nd stone, then 2 units to the 3rd stone, then
2 units to the 4th stone, then 3 units to the 6th stone,
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as
the gap between the 5th and 6th stone is too large.
"""
from alg.label import *
Label(Label.DynamicProgramming, Label.Hash, Label.LinearSpace, Label.LeetCode)

class FrogJump(object):
  def canCross(self, stones):
    if len(stones) == 1:
      return stones == [0]

    dp = {0: {0: True}, 1: {1: True}}
    s = set(stones)
    for i in stones[1:]:
      if i not in dp:
        continue
      for step in dp[i]:
        if step != 1 and i + step - 1 in s:
          if i + step - 1 not in dp:
            dp[i + step - 1] = {}
          dp[i + step - 1][step - 1] = True
        if step != 0 and i + step in s:
          if i + step not in dp:
            dp[i + step] = {}
          dp[i + step][step] = True
        if i + step + 1 in s:
          if i + step + 1 not in dp:
            dp[i + step + 1] = {}
          dp[i + step + 1][step + 1] = True

    return stones[-1] in dp

ins = FrogJump()
print(ins.canCross([0,1]))
print(ins.canCross([0,2147483647]))
print(ins.canCross([0,1,3,5,6,8,12,17]))
print(ins.canCross([0,1,2,3,4,8,9,11]))
print(ins.canCross([0,1,2,3,4]))
