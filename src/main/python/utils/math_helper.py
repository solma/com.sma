def calculate_combination(n, r):
  from math import factorial
  return calculate_permutation(n, r) / factorial(r)

def calculate_permutation(n, r):
  assert n >= r
  from functools import reduce
  return reduce(lambda x, y : x * y, range(n, n - r, -1))

