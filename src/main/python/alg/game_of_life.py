from alg.label import *

Label(Label.BitManipulation, Label.LeetCode)

class Solution(object):
  def game_of_life(self, board):

    def get_cell_status(board, x, y):
      if x < 0 or y < 0 or x >= len(board) or y >= len(board[0]):
        return 0
      return board[x][y] & 1

    dx = (1, 1, 1, 0, 0, -1, -1, -1)
    dy = (1, 0, -1, 1, -1, 1, 0, -1)
    for x in range(len(board)):
      for y in range(len(board[0])):
        lives = 0
        for z in range(8):
          nx, ny = x + dx[z], y + dy[z]
          lives += get_cell_status(board, nx, ny)
        if lives + board[x][y] == 3 or lives == 3:
          board[x][y] |= 2

    for x in range(len(board)):
      for y in range(len(board[0])):
        board[x][y] >>= 1
