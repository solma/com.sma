"""
You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs completely.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
Example 1:

Input: x = 2, y = 6, z = 4
Output: True
Example 2:

Input: x = 2, y = 6, z = 5
Output: False
"""

from alg.label import Label
Label(Label.Math, Label.LeetCode)

class WaterAndJugProblem(object):
  def canMeasureWater(self, x, y, z):
    if x > y:
      x, y = y, x
    gcd = self.gcd(x, y)
    if gcd == 0:
      return z == 0
    return z % gcd == 0 and z <= x + y

  def gcd(self, a, b):
    return self.gcd(b % a, a) if a > 0 else b
