"""
Winter is coming! Your first job during the contest is to design a standard heater with
fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum
radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters separately, and your expected
output will be the minimum radius standard of heaters.

Note:
    Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
    Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
    As long as a house is in the heaters' warm radius range, it can be warmed.
    All the heaters follow your radius standard and the warm radius will the same.
    Example 1:
    Input: [1,2,3],[2]
    Output: 1
    Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard,
    then all the houses can be warmed.
    Example 2:
    Input: [1,2,3,4],[1,4]
    Output: 1
    Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard,
    then all the houses can be warmed.
"""
from alg.label import *

Label(Label.Array, Label.BinarySearch, Label.LeetCode, Label.LinearithmicTime)
import bisect

class Heaters(object):
  def findRadius1(self, houses, heaters) -> int:
    houses = sorted(houses)
    heaters = sorted(heaters)

    p1, p2 = 0, 0
    res = 0
    while p1 < len(houses):
      while p2 < len(heaters) and heaters[p2] < houses[p1]:
        p2 += 1
      if p2 == len(heaters):
        dist = houses[p1] - heaters[p2 - 1]
      elif p2 - 1 >= 0:
        dist = min(heaters[p2] - houses[p1], houses[p1] - heaters[p2 - 1])
      else:
        dist = heaters[p2] - houses[p1]
      res = max(res, dist)
      p1 += 1

    return res

  def findRadius(self, houses, heaters):
    _MAX_BOUNDARY = 10 ** 10
    heaters = sorted(heaters)
    radius, n = 0, len(heaters)
    for house in houses:
      left = bisect.bisect_left(heaters, house)
      dis = (heaters[left] - house) if left < n else _MAX_BOUNDARY
      right = bisect.bisect_right(heaters, house)
      dis = min(dis, (house - heaters[right - 1]) if right > 0 else _MAX_BOUNDARY)
      radius = max(radius, dis)
    return radius


ins = Heaters()
print(ins.findRadius([1, 5], [2]))
print(ins.findRadius([1, 2, 3, 4], [1, 4]))
