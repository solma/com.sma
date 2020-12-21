from utils.decorators import timer

@timer
def quick_select_timer_wrapper(nums, ith):
  return quick_select(nums, ith)

def quick_select(nums, ith):
  n = len(nums)
  assert 0 <= ith <= n - 1
  partition_idx_low, partition_idx_high = partition(nums, int(n / 2))
  if partition_idx_low <= ith <= partition_idx_high:
    return nums[ith]
  if partition_idx_high < ith:
    return quick_select(nums[partition_idx_high + 1:], ith - partition_idx_high - 1)
  return quick_select(nums[:partition_idx_low], ith)

def partition(nums, pivot_idx):
  n = len(nums)
  assert 0 <= pivot_idx <= n - 1
  pivot = nums[pivot_idx]
  low = i = 0
  high = n - 1
  while i <= high:
    if nums[i] > pivot:
      nums[i], nums[high] = nums[high], nums[i]
      high -= 1
    else:
      if nums[i] < pivot:
        nums[i], nums[low] = nums[low], nums[i]
        low += 1
      i += 1
  return low, high

@timer
def base_select(nums, ith):
  return sorted(nums)[ith]

from unittest import TestCase
import random as r
from utils.random_helper import gen_rand_array
NUM_TEST_CASE = 1000
class SortingHelperTest(TestCase):
  def test_quick_select(self):
    for i in range(NUM_TEST_CASE):
      nums = gen_rand_array()
      idx = r.randint(0, len(nums) - 1)
      expected, actual = base_select(nums, idx), quick_select_timer_wrapper(nums, idx)
      try:
        assert expected == actual
      except AssertionError:
        print(nums, idx, expected, actual)

  def test_partition(self):
    for i in range(NUM_TEST_CASE):
      nums = gen_rand_array()
      idx = r.randint(0, len(nums) - 1)
      pivot = nums[idx]
      low_idx, high_idx = partition(nums, idx)
      try:
        assert nums[low_idx] == nums[high_idx] == pivot
      except AssertionError:
        print(nums, idx, pivot, low_idx, high_idx)
