from utils.random_helper import gen_rand_array
from alg.house_robber import rob

def candidate_impl(arr):
  return 0

for _ in range(5):
  arr = [-1, -2, 3]
  arr = gen_rand_array(l=7, max_num=5, can_be_zero=True, can_be_negative=False)
  ground_truth = rob(arr)
  candidate = candidate_impl(arr)
  if ground_truth != candidate:
    print(arr, '\n', ground_truth, ' ', candidate)



