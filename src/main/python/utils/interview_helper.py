from utils.random_helper import gen_rand_array
from alg.house_robber import rob

def candidate_impl(arr):
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

for _ in range(1000):
  # arr = [-1, -2, 3]
  arr = gen_rand_array(l=100, max_num=100, can_be_zero=True, can_be_negative=False)
  ground_truth = rob(arr)
  candidate = candidate_impl(arr)
  if ground_truth != candidate:
    print(arr, '\n', ground_truth, ' ', candidate)



