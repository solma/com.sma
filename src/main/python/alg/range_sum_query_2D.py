"""
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
"""

from src.main.python.alg.label import Label

Label(Label.Array, Label.MatrixGraph, Label.DynamicProgramming, Label.LeetCode)


class NumMatrix(object):
  def __init__(self, matrix):
    self._matrix = matrix
    for r in range(0, len(matrix)):
      for c in range(0, len(matrix[r])):
        self._matrix[r][c] += self._matrix[r][c - 1] if c > 0 else 0
      for c in range(0, len(matrix[r])):
        self._matrix[r][c] += self._matrix[r - 1][c] if r > 0 else 0

  def sumRegion(self, row1, col1, row2, col2):
    return self._matrix[row2][col2] + (self._matrix[row1 - 1][col1 - 1] if row1 > 0 and col1 > 0 else 0) - \
           (self._matrix[row1 - 1][col2] if row1 > 0 else 0) - \
           (self._matrix[row2][col1 - 1] if col1 > 0 else 0)
