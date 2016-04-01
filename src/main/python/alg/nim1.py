"""
Given a pile of stones, two players take turns to remove stones from the pile.
Each player can take either 1 or 2 stones in one turn. Whoever takes the last stone
wins the game. Now suppose the pile has N stones, return true if there is a winning
strategy for the player who plays first.
"""
from alg.label import Label
from src.main.python.lang.decorators.decorator_example import memoize

Label(Label.GameTheory, Label.DynamicProgramming)

@memoize
def IsWin(n):
  if n <= 2:
    return n > 0
  return IsWin(n - 2) and IsWin(n - 3) or IsWin(n - 3) and IsWin(n - 4)

for i in range(1, 11):
  print "%s: %s"%(str(i), str(IsWin(i)))
