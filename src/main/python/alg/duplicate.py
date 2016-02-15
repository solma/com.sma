from random import *

def shuffle(li):
  for i in range(0, len(li)):
    swp = randint(i, len(li) - 1)
    li[i], li[swp] = li[swp], li[i]


def find_duplicate(li):
  for i in range(len(li)):
    while i != li[i]:
      if li[i] == li[li[i]]:
        return li[i]
      else:
        idx = li[i]
        li[i], li[idx] = li[idx], li[i]
        # print li

li = [0, 1, 2, 3, 4, 5, 6, 6, 7]
shuffle(li)
print li
print find_duplicate(li)
print li
