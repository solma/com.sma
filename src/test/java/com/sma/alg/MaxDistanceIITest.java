package com.sma.alg;

import com.sma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MaxDistanceIITest {

  @Test public void testMaxDistance() throws Exception {
    MaxDistanceII ins = new MaxDistanceII();
    for (int i = 0; i < 100; i++) {
      int[] nums = RandomUtil.genRandomArray(10, 10, true, false);
      List<Integer>[] res = new List[2];
      res[0] = dummyMaxDistance(nums);
      res[1] = ins.maxDistance(nums);
      if (res[0].get(0) != res[1].get(0)) {
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(res));
      }
    }
  }

  List<Integer> dummyMaxDistance(int[] nums){
    List<Integer> ret = Arrays.asList(0, -1, -1);
    for(int i = nums.length - 1; i >= 0; i--){
      for(int j = i + 1; j < nums.length; j++){
        if (nums[j] > nums[i] && ret.get(0) < j - i) {
          ret = Arrays.asList(j - i, i, j);
        }
      }
    }
    return ret;
  }
}
