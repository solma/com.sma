"""
Android's lock pattern is a sequence of points drawn on a 3x3 grid:

(0)(1)(2)
(3)(4)(5)
(6)(7)(8)

A valid pattern satisfies all of the following constraints:
1. It must contain 4 to 9 points.
2. It must not contain duplicate points.
3. Point A and point B can be directly connected if no unvisited point C is between them.

For example, the sequence "2641" is invalid because it's not possible to go from point 2 to point 6
without passing through point 4 first. However, the reverse sequence "1462" is valid because point 4
is visited before connecting 6 and 2.

Find out the number of valid patterns.
"""
from alg.label import Label
from utils.decorators import elapsedtime
from utils.decorators import memoize
Label(Label.Backtracking, Label.GraphTheory, Label.Interview, Label.LeetCode)


class AndroidLockPatterns(object):
  @elapsedtime
  def android_lock_patterns(self):
    def is_neighbor(visited, cur_idx, next_idx):
      return (abs(cur_idx % 3 - next_idx % 3) == 1 or
              abs(cur_idx / 3 - next_idx / 3) == 1 or
              ((cur_idx + next_idx) / 2) in visited)

    def hash_func(permutation):
      return (str(set(permutation[:-1])) + '-' + str(permutation[-1])
              if len(permutation) > 0 else None)

    @memoize(hash_func=hash_func)
    def dfs(permutation):
      if len(permutation) == 9:
        return 1
      cnt = 0
      for next_idx in range(9):
        if (next_idx not in permutation and
              (len(permutation) == 0 or is_neighbor(permutation, permutation[-1], next_idx))):
          permutation.append(next_idx)
          cnt += dfs(permutation)
          permutation.remove(next_idx)
      cnt += 1 if len(permutation) >= 4 else 0
      return cnt

    print "cnt = " + str(dfs([]))

ins = AndroidLockPatterns()
ins.android_lock_patterns()





