import random as rd
import math as m


N = 1000000
li = []
for i in range(N):
    li.append(rd.randint(1, N))

"""
 direct hash
"""
bm = [0] * int(m.ceil(N / 8.0))
for r in li:
    bm[(r - 1) / 8] |= 0x1 << (r - 1) % 8
cnt = 0
# print bm
for e in bm:
    for i in range(8):
        if e >> i & 0x1 == 1:
            cnt += 1
print cnt

"""
sampling
"""
import sys

minimum = 1
for r in li:
    minimum = min(minimum, r / (N - 1.0))
print 1 / minimum - 1


