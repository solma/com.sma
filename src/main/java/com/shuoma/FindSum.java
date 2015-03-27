package com.shuoma;
//Given an array, find a set of array members whose sum equals to a given number.
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;
import static com.shuoma.annotation.Tag.Trick.FromTwoEndsToMiddle;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.HashMap;

@Tag(dss = Array, source = LeetCode, tricks = FromTwoEndsToMiddle)
public class FindSum {
  public static void main(String[] args) {
    new FindSum().main();
  }

  void main() {
    int[] a = {1, 3, -1, 1, 0, 9, 6, -5, 3, 5, -3, 7, 4, -4};
    int sum = 10;
    findPairs1(a, sum);
    //findPairs2(a, sum);
    findTriplets(a, sum);
  }

  void findPairs1(int[] a, int sum) {
    int len = a.length;
    Arrays.sort(a);
    int low = 0, high = len - 1;
    while (low <= high) {
      if (a[low] + a[high] == sum) {
        System.out.println("(" + a[low] + "," + a[high] + ")");
        if (low + 1 < len && a[low] == a[low + 1])
          low++;
        else
          high--;
      } else if (a[low] + a[high] > sum)
        high--;
      else
        low++;
    }
  }

  void findTriplets(int[] a, int sum) {
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
      findPairs1(a, sum - a[i]);
      System.out.println();
    }

  }


  void findPairs2(int[] a, int sum) {
    HashMap<Integer, Integer> ht = new HashMap<Integer, Integer>();
    int i;
    for (i = 0; i < a.length; i++)
      if (!ht.containsKey(new Integer(a[i])))
        ht.put(new Integer(a[i]), new Integer(1));
      else {
        int fq = ht.remove(Integer.valueOf(a[i])).intValue();
        ht.put(Integer.valueOf(a[i]), Integer.valueOf(fq + 1));
      }
    int j;
    for (i = 0; i < a.length; i++)
      if (ht.containsKey(sum - a[i]) && a[i] <= sum - a[i])
        for (j = 0; j < ht.get(sum - a[i]).intValue(); j++)
          System.out.println("(" + a[i] + "," + (sum - a[i]) + ")");
  }
}
