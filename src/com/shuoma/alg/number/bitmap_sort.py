import random as rd
import math as m
li=[]
n=26
for i in range(n):
    li.append(rd.randint(0,n-1))
print li
bm=[0x0]*int(m.ceil(n/8.0))
print bm
for i in range(len(li)):
    bm[i/8]|=0x1<<(li[i]%8)
    print li[i], 0x1<<li[i]%8, bin(bm[i/8])

li1=[]
for i in range(len(li)):
    if bm[i/8]>>i%8 & 0x1 == 1:
	li1.append(i)
print li1