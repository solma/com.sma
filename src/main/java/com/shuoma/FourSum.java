package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;
import static com.shuoma.annotation.Tag.Trick.FromTwoEndsToMiddle;

import com.shuoma.annotation.Tag;

import java.util.*;

@Tag(dss = Array, source = LeetCode, tricks = FromTwoEndsToMiddle)
public class FourSum {
  public static void main(String[] args) {
    FourSum ins = new FourSum();
    for (List<Integer> li : ins.fourSum(new int[] {-1, 0, -5, -2, -2, -4, 0, 1, -2}, -9)) {
      System.out.println(li);
    }
  }

  public List<List<Integer>> fourSum(int[] num, int target) {
    // Start typing your Java solution below
    // DO NOT write main() function
    Arrays.sort(num);
    Set<List<Integer>> ret = new HashSet<>();
    for (int i = 0; i < num.length; i++) {
      //System.out.println(num[i]);
      for (List<Integer> triplet : threeSum(num, i, target - num[i])) {
        //System.out.println(triplet);
        triplet.add(num[i]);
        Collections.sort(triplet);
        ret.add(triplet);
      }
      //System.out.println();
    }
    return new ArrayList<>(ret);
  }


  public List<List<Integer>> threeSum(int[] num, int forbidden, int target) {
    Set<List<Integer>> lstSoln = new HashSet<>();

    List<Integer> tempArr;
    for (int i = 0; i < num.length; i++) {
      if (i == forbidden)
        continue;
      if (num[i] > target && target >= 0)
        break;
      int j = i + 1;
      int k = num.length - 1;
      while (j < k) {
        int sum3 = num[i] + num[j] + num[k];
        if (sum3 < target) {
          j++;
        } else if (sum3 > target) {
          k--;
        } else {
          if (j == forbidden || k == forbidden)
            break;
          tempArr = new ArrayList<>();
          Collections.addAll(tempArr, num[i], num[j], num[k]);
          lstSoln.add(tempArr);
          j++;
          k--;
        }
      }
    }
    return new ArrayList<>(lstSoln);

  }

}
