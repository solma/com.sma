from utils import random_helper
from utils import decorators
from alg.label import *

Label(Label.BitManipulation, Label.Sorting)

@decorators.timer
def bitmap_sort(li):
  _BYTE_SIZE = 8
  l, r = min(li), max(li)
  bm = [0x0] * int((r - l) / _BYTE_SIZE + 1)
  for e in li:
    e -= l
    bm[int(e / _BYTE_SIZE)] |= 0x1 << (e % _BYTE_SIZE)

  sorted_li = []
  for i in range(len(bm)):
    for j in range(_BYTE_SIZE):
      if bm[i] & (1 << j) == 0:
        continue
      offset = i * _BYTE_SIZE + j
      sorted_li.append(offset + l)
  return sorted_li

@decorators.timer
def builtin_sort(li):
  return sorted(set(li))

for i in range(5):
  arr = random_helper.gen_rand_array(10 ** 6, 1000, True, False)
  ans1, ans2 = bitmap_sort(arr), builtin_sort(arr)
  if ans1 != ans2:
    print(arr, '\n', 'ans1=', ans1, '\nans2=', ans2)

