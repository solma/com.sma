package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Subarray;
import static com.sma.annotation.Tag.Reference.Interview;
import com.sma.annotation.Tag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a int array, both positive and negative numbers,
 * write code to check if there is a sub array which sums to zero.
 * Requirement O(n) time
 */
@Tag(dss = Subarray, references = Interview)
public class ZeroSumSubArray {

  public static void main(String[] args) {
    ZeroSumSubArray ins = new ZeroSumSubArray();
    int[] num = new int[]{2, 3, -1, 5, -7, 1, 2};
    System.out.println(Arrays.toString(ins.zeroSumSubarray(num)));
  }

  int[] zeroSumSubarray(int[] num) {
    Map<Integer, Integer> sumToIdx = new HashMap<>();
    int sum = 0;
    for (int i = 0; i < num.length; i++) {
      sum += num[i];
      if (sumToIdx.containsKey(sum)) {
        return new int[]{sumToIdx.get(sum) + 1, i};
      }
      sumToIdx.put(sum, i);
    }
    return null;
  }
}
