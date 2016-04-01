package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Difficulty.D1;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

@Tag(dl = D1, dss = {Array}, references = LeetCode)
public class SummaryRanges {

  public List<String> summaryRanges(int[] nums) {
    List<String> ret = new LinkedList<>();
    int n = nums.length;
    if (n == 0) return ret;
    int rangeStart = nums[0], prev = nums[0];
    for (int i = 1; i < n; i++) {
      if (nums[i] > prev + 1) {
        int diff = prev - rangeStart;
        ret.add(diff == 0 ? String.valueOf(rangeStart) : (rangeStart + "->" + prev));
        rangeStart = nums[i];
      }
      prev = nums[i];
    }

    int diff = prev - rangeStart;
    ret.add(diff == 0 ? String.valueOf(rangeStart) : (rangeStart + "->" + prev));
    return ret;
  }
}


