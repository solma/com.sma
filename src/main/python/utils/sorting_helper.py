def quick_select(nums, ith):
  n = len(nums)
  assert 0 <= ith <= n - 1
  partition_idx_low, partition_idx_high = partition(nums, n / 2)
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
