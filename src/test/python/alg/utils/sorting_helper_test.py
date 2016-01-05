from unittest import TestCase

from src.main.python.alg.util.sorting_helper import *
from utils.random_helper import *

NUM_TEST_CASE = 1000

class SortingHelperTest(TestCase):
  def test_quick_select(self):
    for i in range(NUM_TEST_CASE):
      nums = gen_rand_array()
      idx = r.randint(0, len(nums) - 1)
      sorted_nums = sorted(nums)
      expected, actual = sorted_nums[idx], quick_select(nums, idx)
      try:
        assert expected == actual
      except AssertionError:
        print nums, sorted_nums, idx, expected, actual

  def test_partition(self):
    for i in range(NUM_TEST_CASE):
      nums = gen_rand_array()
      idx = r.randint(0, len(nums) - 1)
      pivot = nums[idx]
      low_idx, high_idx = partition(nums, idx)
      try:
        assert nums[low_idx] == nums[high_idx] == pivot
      except AssertionError:
        print nums, idx, pivot, low_idx, high_idx
