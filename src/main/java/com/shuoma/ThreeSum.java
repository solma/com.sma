package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;
import static com.shuoma.annotation.Tag.Trick.FromTwoEndsToMiddle;

import com.shuoma.annotation.Tag;

import java.util.*;

@Tag(dss = Array, source = LeetCode, tricks = FromTwoEndsToMiddle)
public class ThreeSum {
  public static void main(String[] args) {
    ThreeSum ins = new ThreeSum();
    for (ArrayList<Integer> li : ins.threeSum(new int[] {-25, -10, -7, -3, 0, 2, 4, 8, 10})) {
      System.out.println(li);
    }
  }


  public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    HashSet<ArrayList<Integer>> lstSoln = new HashSet<ArrayList<Integer>>();

    ArrayList<Integer> tempArr = null;
    for (int i = 0; i < num.length; i++) {
      if (num[i] > 0)
        break; //tricks, terminates early
      int j = i + 1;
      int k = num.length - 1;
      while (j < k) {
        int sum3 = num[i] + num[j] + num[k];
        if (sum3 < 0) {
          j++;
        } else if (sum3 > 0) {
          k--;
        } else {
          tempArr = new ArrayList<Integer>();
          Collections.addAll(tempArr, num[i], num[j], num[k]);
          lstSoln.add(tempArr);
          j++;
          k--;
        }
      }
    }

    return new ArrayList<ArrayList<Integer>>(lstSoln);

  }

}
