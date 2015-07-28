package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.SlidingWindow;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 *  Given an array, check if two elements in array satisfy that
 *  Math.abs(a[i] - a[j]) <= L and Math.abs(i - j) <= K
 */
@Tag(algs = SlidingWindow, dss = {Array, Hash}, references = Interview)
public class ValidArrayWindow {

  public static void main(String[] args) {
    ValidArrayWindow ins = new ValidArrayWindow();
    System.out.println(ins.validArrayWindowExists(new int[] {11, 13, 20}, 2, 10));
  }

  /** This only check if such a pair exists; cannot get all such pairs. */
  boolean validArrayWindowExists(int[] array, int K, int L) {
    Map<Integer, Integer> hashMap = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      if (i >= K) hashMap.remove(array[i - K] / (L + 1));
      System.out.println(i + " " + hashMap);
      int key = array[i] / (L + 1);
      if (hashMap.containsKey(key) ||
          hashMap.containsKey(key - 1) && Math.abs(hashMap.get(key - 1) - array[i]) <= L ||
          hashMap.containsKey(key + 1) && Math.abs(hashMap.get(key + 1) - array[i]) <= L) {
          return true;
      }
      hashMap.put(key, array[i]);
    }
    return false;
  }
}
