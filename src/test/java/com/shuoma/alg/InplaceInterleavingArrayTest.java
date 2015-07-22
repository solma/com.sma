package com.shuoma.alg;

import com.shuoma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;

public class InplaceInterleavingArrayTest {

  @Test public void testInterleave() throws Exception {
    InplaceInterleavingArray ins = new InplaceInterleavingArray();
    for (int times = 0; times < 100; times++) {
      int[] nums = RandomUtil.genRandomArray(10, 10, true, false);
      int[][] res = new int[2][];
      res[0] = ins.interleave(Arrays.copyOf(nums, nums.length));
      res[1] = ins.interleave1(Arrays.copyOf(nums, nums.length));
      if (!Arrays.equals(res[0], res[1])) {
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
      }
    }
  }
}
