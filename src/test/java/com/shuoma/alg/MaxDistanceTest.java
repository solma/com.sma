package com.shuoma.alg;

import com.shuoma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;

public class MaxDistanceTest {

  @Test
  public void testMaxDistance() throws Exception {
    MaxDistance ins = new MaxDistance();
    for (int i = 0; i < 100; i++) {
      int[] nums = RandomUtil.genRandomArray(6, 10, true, false);
      //int[] nums = {8, 5, 6, 1, 11, 9};
      int[][] res = new int[3][3];
      res[0] = dummyMaxDistance(nums);
      res[1] = ins.maxDistance(nums);
      res[2] = ins.maxDistanceTwoPointersFail(nums);
      if (res[0][0] != res[1][0]) {
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
        System.out.println(Arrays.toString(res[2]));
      }
    }
  }

  public int[] dummyMaxDistance(int[] nums){
    int[] ret = new int[3];
    ret[0] = Integer.MIN_VALUE;
    for(int i = nums.length - 1; i >= 0; i--){
      for(int j = i; j < nums.length; j++){
        int distance = nums[i] + nums[j] + j - i;
        if (distance > ret[0]) {
          ret[0] = distance;
          ret[1] = i;
          ret[2] = j;
        }
      }
    }

    return ret;
  }
}
