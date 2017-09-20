import random as r

def gen_rand_array(l = 10, max_num = 100, can_be_zero = False, can_be_negative = False):
  return [r.randint(0 if can_be_zero else 1, max_num) * (-1 if can_be_negative and r.randint(0, 2) > 0 else 1)
          for _ in range(l)]
