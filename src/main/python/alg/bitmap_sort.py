import random as rd
import math

li = []

n = 26
for i in range(n):
  li.append(rd.randint(0, n - 1))
print(li)


def bitmap_sort(li):
  print(li)
  bm = [0x0] * int(math.ceil(n / 8.0))
  print(bm)
  for i in range(len(li)):
    bm[int(i / 8)] |= 0x1 << (li[i] % 8)
    print(li[i], 0x1 << li[i] % 8, bin(bm[int(i / 8)]))

  li1 = []
  for i in range(len(li)):
    if bm[int(i / 8)] >> i % 8 & 0x1 == 1:
      li1.append(i)
  return li1

print(bitmap_sort(li))

