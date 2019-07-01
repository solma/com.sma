import random as r

def gen_rand_array(l = 10, max_num = 100, can_be_zero = False, can_be_negative = False):
  return [r.randint(0 if can_be_zero else 1, max_num) * (-1 if can_be_negative and r.randint(0, 2) > 0 else 1)
          for _ in range(l)]

def gen_rand_str(l = 10):
  ret = ''
  for _ in range(l):
    i = r.randint(0, 51)
    if i < 26:
      ret += chr(ord('a') + i)
    else:
      ret += chr(ord('A') + i - 26)
  return ret
