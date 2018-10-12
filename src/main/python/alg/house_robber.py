from alg.label import Label

Label(Label.DynamicProgramming, Label.LeetCode, Label.LinearithmicTime)

def rob(arr):
  n = len(arr)
  if n < 3:
    return max(arr)
  # prev/cur is the max utility at current/previous index
  prev, cur = 0, arr[0]
  for i in range(1, n):
    # cur = case not using arr[i];   max(prev, 0) + arr[i] = case using arr[i]
    tmp = max(cur, max(prev, 0) + arr[i])
    prev = cur
    cur = tmp
  return cur

