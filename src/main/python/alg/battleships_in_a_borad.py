"""
Given an 2D board, count how many different battleships are in it. The battleships are
represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of
the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent
battleships.

Example:
X..X
...X
...X
In the above board there are 2 battleships.

Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell
separating between them.
"""
from alg.label import Label

Label(Label.Array, Label.LeetCode)

class BattleshipsInABoard(object):
  def countBattleships(self, board):
    n = len(board)
    m = len(board[0])
    cnt = 0
    for i in range(n):
      for j in range(m):
        if (board[i][j] == 'X' and
              (i == 0 or board[i - 1][j] == '.') and
              (j == 0 or board[i][j - 1] == '.')):
          cnt += 1
    return cnt

ins = BattleshipsInABoard()
for board in [
  [['X', '.', '.', 'X'], ['.', '.', '.', 'X'], ['.', '.', '.', 'X']],
]:
  print(ins.countBattleships(board))
