package com.sma.alg;

import com.sma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TwoDifferenceTest {

  @Test
  public void testTwoDifference() throws Exception {
    TwoDifference ins = new TwoDifference();
    for (int times = 0; times < 100; times++) {
      int[] nums = RandomUtil.genRandomArray(10, 10, true, false);
      Arrays.sort(nums);
      int target = RandomUtil.r.nextInt(10);
      List<Integer> ret = ins.twoDifference(nums, target);

      List<List<Integer>> dummyRet = dummyTwoDifference(nums, target);
      if (ret.size() == 0 && dummyRet.size() > 0 ||
          ret.size() > 0 && ret.get(0) - ret.get(1) != target) {
        System.out.println(target + " " + Arrays.toString(nums));
        System.out.println(ret);
        System.out.println(dummyRet);
      }
    }
  }

  List<List<Integer>> dummyTwoDifference(int[] nums, int target) {
    int n = nums.length;
    List<List<Integer>> ret = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (nums[i] - nums[j] == target) {
          ret.add(Arrays.asList(nums[i], nums[j]));
        }
      }
    }
    return ret;
  }
}
