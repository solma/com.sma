class FrogJump(object):
  def canCross(self, stones):
    n = len(stones)
    if n == 1:
      return stones == [0]
    if n == 2:
      return stones == [0, 1]
    dp = [[True], [True]] + [[False] * i for i in range(2, n)]
    for i in range(1, n):
      for step in range(1, len(dp[i]) + 1):
        if not dp[i][step - 1]:
          continue
        if i + step - 1 < n and step >= 2 and stones[i + step - 1] == stones[i] + step - 1:
          dp[i + step - 1][step - 2] = True
        if i + step < n and stones[i + step] == stones[i] + step:
          dp[i + step][step - 1] = True
        if i + step + 1 < n and stones[i + step + 1] == stones[i] + step + 1:
          dp[i + step + 1][step] = True

    for line in dp:
      print line
    for i in range(len(dp[n - 1])):
      if dp[n - 1][i]:
        return True
    return False

ins = FrogJump()
print ins.canCross([0,1,3,5,6,8,12,17])
print ins.canCross([0,1,2,3,4,8,9,11])
