package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.InplaceSwap;
import static com.shuoma.util.MathUtil.gcd;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dl = D3, dss = Array, reference = LeetCode, tricks = InplaceSwap)
/** Rotate right. */
public class RotateArray {

  public static void main(String[] args) {
    int[] a = new int[] {1, 2, 3, 4, 5, 6};
    new RotateArray().rotate(a, 11);
    System.out.println(Arrays.toString(a));
  }

  public void rotate(int[] nums, int k) {
    int n = nums.length;
    k %= n;
    rotateLeft(nums, n - k);
  }

  void rotateLeft(int[] nums, int k) {
    int n = nums.length;
    if (k == 0 || n == 1)
      return;
    int noOfCycles = gcd(k, n);
    int cycleSize = n / noOfCycles;

    for (int j = 0; j < noOfCycles; j++) {
      int start = nums[j];
      int i;
      for (i = 0; i < cycleSize - 1; i++) {
        nums[(j + i * k) % n] = nums[(j + (i + 1) * k) % n];
      }
      nums[(j + i * k) % n] = start;
    }
  }
}
