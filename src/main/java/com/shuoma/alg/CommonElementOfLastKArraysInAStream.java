package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.SlidingWindow;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.Reference.Interview;
import static com.shuoma.util.ArrayUtil.intArrayToIntegerList;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Find common elements in N sorted arrays with no extra space
 */
@Tag(algs = SlidingWindow, dss = {Array, Hash}, references = Interview)
public class CommonElementOfLastKArraysInAStream {
  public static void main(String[] args) {
    int[][] a = {
        {10, 160, 200, 500, 500,},
        {4, 150, 160, 170, 520,},
        {2, 160, 200, 202, 203,},
        {3, 150, 155, 160, 300},
        {3, 150, 155, 160, 301}
    };

    new CommonElementOfLastKArraysInAStream().commonElementOfLastKArraysInAStream(a, 2);
  }

  void commonElementOfLastKArraysInAStream(int[][] stream, int k) {
    List<Set<Integer>> lastKArrays = new ArrayList<>(k);
    Map<Integer, Integer> cnt = new HashMap<>();
    for (int[] ary : stream) {
      // remove the last Kth array
      if (lastKArrays.size() == k) {
        Set<Integer> removed = lastKArrays.remove(0);
        for (int x : removed) {
          cnt.put(x, cnt.get(x) - 1);
        }
      }

      // add the current array
      Set<Integer> addSet = new HashSet<>(intArrayToIntegerList(ary));
      lastKArrays.add(addSet);
      for (int x : addSet) {
        if (!cnt.containsKey(x)) cnt.put(x, 0);
        cnt.put(x, cnt.get(x) + 1);
        if (cnt.get(x) == k)
          System.out.print(x + " ");
      }
      System.out.println();
    }
  }
}
