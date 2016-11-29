package com.sma.alg;

import com.sma.annotation.Tag;
import com.sma.util.RandomUtil;

import java.util.*;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Algorithm.Recursion;

/***
 * Given an array of numbers, partition the array into two subsets whose size differ at most by 1,
 * find the min difference between the sum of the two sub sets.
 */

@Tag(algs = {Arithmetic, Recursion})
public class SubsetSum {

  /** Choose half elements. */
  public static void partitionProblem(int[] arr) {
    int sum = 0;
    for (int n : arr)
      sum += n;
    int[] minDiff = new int[] {sum};
    System.out.println(partitionProblem(arr, sum, new HashSet<Integer>(), 0, minDiff));
    System.out.println(minDiff[0]);
  }

  private static List<List<Integer>> partitionProblem(int[] arr, int sum, Set<Integer> set,
      int setSum, int[] minDiff) {
    List<List<Integer>> res = new ArrayList<>();
    int n = arr.length;
    if (set.size() == n / 2) {
      // alternative equation Math.abs(setSum - sum/2)
      if (Math.abs(sum - 2 * setSum) < minDiff[0]) {
        minDiff[0] = Math.abs(sum - 2 * setSum);
        res.add(new ArrayList<Integer>());
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < n; i++) {
          if (set.contains(i))
            res.get(0).add(arr[i]);
          else
            res.get(1).add(arr[i]);
        }
        System.out.println(res + " " + minDiff[0] + " " + setSum + " " + (sum - setSum));
      }
    }
    for (int i = 0; i < arr.length; i++) {
      if (set.contains(i)) continue;
      set.add(i);
      List<List<Integer>> tmp = partitionProblem(arr, sum, set, setSum + arr[i], minDiff);
      if (!tmp.isEmpty()) res = tmp;
      set.remove(new Integer(i));
    }
    return res;
  }

  public static void main(String[] args) {
    int[] arr = RandomUtil.genRandomArray(7, 10, true, false);
    System.out.println(Arrays.toString(arr));
    partitionProblem(arr);
  }
}
