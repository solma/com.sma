package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.FromTwoEndsToMiddle;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(dss = Array, references = LeetCode, tricks = FromTwoEndsToMiddle)
public class ThreeSum {
  public static void main(String[] args) {
    ThreeSum ins = new ThreeSum();
    for (List<Integer> li : ins.threeSum(new int[] {-25, -10, -7, -3, 0, 2, 4, 8, 10})) {
      System.out.println(li);
    }
  }

  public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    Set<List<Integer>> lstSoln = new HashSet<>();

    for (int i = 0; i < num.length; i++) {
      if (num[i] > 0) { break; }//tricks, terminates early
      int j = i + 1;
      int k = num.length - 1;
      while (j < k) {
        int sum3 = num[i] + num[j] + num[k];
        if (sum3 < 0) {
          j++;
        } else if (sum3 > 0) {
          k--;
        } else {
          lstSoln.add(Arrays.asList(num[i], num[j], num[k]));
          j++;
          k--;
        }
      }
    }

    return new ArrayList<>(lstSoln);
  }
}
