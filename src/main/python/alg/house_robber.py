from alg.label import Label

Label(Label.DynamicProgramming, Label.LeetCode, Label.LinearithmicTime)

def rob(arr):
  n = len(arr)
  if n < 3:
    return max(arr)
  # prev/cur is the max utility at current/previous index
  prev, cur = 0, arr[0]
  ret = cur
  for i in range(1, n):
    tmp = max(cur, prev + max(0, arr[i]))
    prev = cur
    cur = tmp
    ret = max(ret, cur)
  return ret

