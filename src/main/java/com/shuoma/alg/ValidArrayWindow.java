package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.HashTable;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 *  Given an array, check if two elements in array satisfy that
 *  Math.abs(a[i] - a[j]) <= L and Math.abs(i - j) <= K
 */
@Tag(dss = {Array, HashTable})
public class ValidArrayWindow {

  /** This only check if such window exists; cannot locate the exact window. */
  public boolean validArrrayWindowExists(int[] array, int K, int L) {
    Map<Integer, Integer> hashMap = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      if (i >= K) hashMap.remove(array[i - K] / (L + 1));
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
