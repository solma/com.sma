from alg.label import *
from utils import random_helper as rh
from utils.decorators import memoize
from utils.decorators import timer
from collections import defaultdict
import itertools

Label(Label.DynamicProgramming, Label.Backtracking)

# Requires python3 because use '/' for float division.

HAND_SIZE = 4

@timer
def game_24(hand, target=24):

  @memoize(hash_func=str)
  def solve(idx_set):
    expressions = defaultdict(set) # key: target, value: expressions that produces the target

    def add_expressions(part1_exprs, v1, part2_exprs, v2, key, op_str):
      for expr1 in part1_exprs[v1]:
        for expr2 in part2_exprs[v2]:
          expressions[key].add('( %s ) %s ( %s )' % (expr1, op_str, expr2))

    n = len(idx_set)
    if n == 1:
      v = hand[list(idx_set)[0]]
      expressions[v].add(str(v))
    else:
      for i in range(1, len(idx_set)//2 + 1):
        combs = itertools.combinations(idx_set, i)
        for part1 in combs:
          part1 = set(part1)
          part2 = idx_set - part1

          part1_exprs = solve(part1)
          part2_exprs = solve(part2)
          for v1 in part1_exprs.keys():
            for v2 in part2_exprs.keys():
              sum = v1 + v2
              if n < HAND_SIZE or sum == target:
                add_expressions(part1_exprs, v1, part2_exprs, v2, sum, '+')

              product = v1 * v2
              if n < HAND_SIZE or product == target:
                add_expressions(part1_exprs, v1, part2_exprs, v2, product, '*')

              diff = v1 - v2
              if n < HAND_SIZE or diff == target:
                add_expressions(part1_exprs, v1, part2_exprs, v2, diff, '-')
              diff = v2 - v1
              if n < HAND_SIZE or diff == target:
                add_expressions(part2_exprs, v2, part1_exprs, v1, diff, '-')

              if v2 != 0:
                div = v1 / v2 * 1.0
                if n < HAND_SIZE or div == target:
                  add_expressions(part1_exprs, v1, part2_exprs, v2, div, '/')
              if v1 != 0:
                div = v2 / v1 * 1.0
                if n < HAND_SIZE or div == target:
                  add_expressions(part2_exprs, v2, part1_exprs, v1, div, '/')

    return expressions

  return solve(set(range(4)))[target]

hands = [
  [5, 1, 5, 5],
  [2, 3, 10, 10],
  [3, 3, 8, 8],
  [4, 4, 10, 10],
  [2, 5, 7, 13],
  [2, 2, 2, 6],
  [11, 11, 1, 5],
  [11, 13, 1, 6]
]
hands = [rh.gen_rand_array(l=4, max_num=13, can_be_zero=False, can_be_negative=False) for _ in range(100)]
for hand in hands:
  print(str(hand))
  sols = game_24(hand)
  print('\n'.join(sols))
  print('\n')




