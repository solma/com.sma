"""
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:
nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
"""
import itertools

from alg.label import Label

Label(Label.DFS, Label.TopologicalSorting, Label.LeetCode)


class LongestIncreasingPathInAMatrix(object):
  def longestIncreasingPath(self, matrix):
    def neighbors(cur_pos):
      row, col = cur_pos[0], cur_pos[1]
      ret = []
      for dr, dc in zip([1, 0, -1, 0], [0, 1, 0, -1]):
        nr = row + dr
        if nr < 0 or nr >= m:
          continue
        nc = col + dc
        if nc < 0 or nc >= n:
          continue
        ret.append((nr, nc))
      return ret

    def dfs(cur):
      for nxt in neighbors(cur):
        if matrix[nxt[0]][nxt[1]] <= matrix[cur[0]][cur[1]]:
          continue
        if dp[nxt] == 1:
          dp[nxt] = dfs(nxt)
        dp[cur] = max(dp[cur], dp[nxt] + 1)
      return dp[cur]

    if len(matrix) == 0 or len(matrix[0]) == 0:
      return 0
    m, n = len(matrix), len(matrix[0])
    dp = dict()
    for pos in itertools.product(range(m), range(n)):
      dp[pos] = 1
    for pos in itertools.product(range(m), range(n)):
      if dp[pos] > 1:
        continue
      dfs(pos)
    return dp[max(dp, key = dp.get)]
