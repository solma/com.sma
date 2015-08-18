package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.CheckAtEveryIndex;
import static com.shuoma.annotation.Tag.Trick.FromTwoEndsToMiddle;

import com.shuoma.annotation.Tag;

@Tag(algs = Greedy, dl = D2, dss = Subarray, references = LeetCode, tricks = {CheckAtEveryIndex, FromTwoEndsToMiddle})
public class ContainerWithMostWater {

  public int maxArea(int[] height) {
    int l = 0, r = height.length - 1, res = 0;
    while (l < r) {
      res = Math.max(res, Math.min(height[l], height[r]) * (l - r));
      if(height[l] < height[r]){
        l++;
        while(l < r && height[l] <= height[l - 1]) l++;
      } else {
        r--;
        while(l < r && height[r] <= height[r + 1]) r--;
      }
    }
    return res;
  }
}
