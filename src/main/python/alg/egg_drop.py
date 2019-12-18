import sys

from alg.label import Label

Label(Label.BinarySearch, Label.DynamicProgramming, Label.QuadraticTime)

# n=int(sys.stdin.readline().rstrip('\n'))
n = 1  # number of cases

t = []
MAX_N = 101
INF = 1 << 32


def rc(d, b):
  return 1 + rc(d - 1, b - 1) + rc(d - 1, b) if d > 1 and b > 1 else d


def g(d, b):
  global t
  b = min(d, b)
  if d < MAX_N and b < MAX_N: return t[d][b]
  if b == 0: return 0
  if b == 1: return d
  if b == 2:
    x = d * (d + 1) / 2
    return min(x, INF)
  if b == 3:
    x = d ** 2 + 5
    x = min(x, INF)
    x *= d / 6
    return min(x, INF)
  return INF


def getd(f, d, b):
  low = 0
  high = d
  while high > low + 1:
    m = int((low + high) / 2)
    if g(m, b) < f:
      low = m
    else:
      high = m
  return high


def getb(f, d, b):
  b = min(d, b)
  low = 0
  high = b
  while high > low + 1:
    m = int((low + high) / 2)
    if g(d, m) < f:
      low = m
    else:
      high = m
  return high


"""
#this is wrong
def dp(f, d, b):
    res=[0]*3
    br=False
    for i in range(1, d+1):
        if br: break
        for j in range(1, b+1):
            if res==[0,0,0] and t[i][j]>=f:
                res=[t[d][b],i,j-1]
                br=True
                break
    #for row in range(d): print t[row][:b+1]
    if t[d][b]==INF: res[0]=-1
    return res
"""

for case in range(n):
  print("input #floor #drop #egg")
  f, d, b = map(int, sys.stdin.readline().rstrip('\n').split(' '))
  # print(rc(d, b))
  # generate dp table
  for i in range(MAX_N):
    if i == 1:
      t.append([0] + [1] * (MAX_N - 1))
    else:
      t.append([0] * MAX_N)
  # print(t[:2])
  for i in range(1, MAX_N):
    for j in range(1, MAX_N):
      x = t[i - 1][j]
      y = t[i - 1][j - 1]
      if x == INF or y == INF or x + y + 1 >= INF:
        t[i][j] = INF
      else:
        t[i][j] = x + y + 1

  fmax = g(d, b)
  if fmax >= INF: fmax = -1
  dmin = getd(f, d, b)
  bmin = getb(f, d, b)
  print("Case #%d: %d %d %d" % (case + 1, fmax, dmin, bmin))

  # print("Case #%d: %s"%(case+1, ' '.join(map(str, dp(f,d,b))))
