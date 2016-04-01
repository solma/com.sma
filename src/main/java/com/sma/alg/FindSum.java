package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.Array;

import com.sma.annotation.Tag;
import com.sma.util.CollectionsUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an array, and a number, find all combinations of elements in the array that the sum
 * equals to the given number, subject to the tabu array.
 */
@Tag(algs = DynamicProgramming, dss = Array)
public class FindSum {

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 5};
    boolean[] forbidden = new boolean[arr.length];
    //forbidden[5] = true;
    System.out.println(findSum(arr, 5, forbidden));
  }

  public static List<List<Integer>> findSum(int[] arr, int sum, boolean[] forbidden) {
    Arrays.sort(arr);
    Map<Integer, List<List<Integer>>> cache = new HashMap<>();
    cache.put(0, new LinkedList<List<Integer>>());
    cache.get(0).add(new LinkedList<Integer>());

    for (int i = 0; i < arr.length; i++) {
      if (forbidden[i]) {
        continue;
      }
      for (int s = arr[0]; s <= sum; s++) {
        if (s >= arr[i] && cache.containsKey(s - arr[i])) {
          for (List<Integer> pre : cache.get(s - arr[i])) {
            if (pre.contains(i)) {
              continue;
            }
            List<Integer> cpy = new LinkedList<>(pre);
            cpy.add(i);
            CollectionsUtil.upsert(cache, s, cpy, new LinkedList<List<Integer>>());
          }
        }
      }
    }

    List<List<Integer>> ret = cache.get(sum);
//    List<List<Integer>> retValues = new LinkedList<>();
//    for (List<Integer> index : ret) {
//      retValues.add(CollectionsUtil.toValue(arr, index));
//    }
//    return retValues;
    List<List<Integer>> empty = new LinkedList<>();
    return ret == null ? empty : ret;
  }
}
