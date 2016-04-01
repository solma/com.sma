package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Sorting;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.util.ArrayUtil;

@Tag(algs = Sorting, dss = Array, references = LeetCode)
public class KthLargestElementInAnArray {

  public static void main(String[] args) {
    int[][] cases = new int[][] {
        //{3, 5, 1, 2, 6},
        //{3, 3, 1, 3, 3},
        //{5, 2, 4, 1, 3, 6, 0},
        //{3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6},
        {-787, -740, -515, -176, 333, 335, 547, 647, 444, 652, 940, 660}
    };
    KthLargestElementInAnArray ins = new KthLargestElementInAnArray();
    for (int[] cse : cases) {
      System.out.println(ins.findKthLargest(cse, 2));
    }
  }

  public int findKthLargest(int[] nums, int k) {
    int nk = nums.length + 1 - k;
    findKthSmallest(nums, 0, nums.length - 1, nk);
    return nums[nk - 1];
  }

  public void findKthSmallest(int[] nums, int sIdx, int eIdx, int k) {
    if (sIdx > eIdx) return;
    int pIdx = ArrayUtil.partition(nums, sIdx, eIdx, sIdx + ((eIdx - sIdx) >> 1));
    //System.out.println(Arrays.toString(nums) + " " + sIdx + " " + eIdx + " " + k + " " + pIdx);
    int offset = pIdx - sIdx;
    if (offset == k - 1) {
      return;
    } else {
      if (offset < k - 1) {
        findKthSmallest(nums, pIdx + 1, eIdx, k - (offset + 1));
      } else {
        findKthSmallest(nums, sIdx, pIdx - 1, k);
      }
    }
  }
}
