package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.HashTable;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// find all numbers whose appearance takes at least 1/m of the population
// reference: wechat

@Tag(dss = HashTable)
public class ModeNumber {
  public static void main(String[] args) {
    new ModeNumber().modeNumber(new int[] {1, 3, 2, 2, 3, 2, 3, 1}, 3);
  }

  // linear time and constant space
  public int[] modeNumber(int[] num, int m) {
    Map<Integer, Integer> hm = new HashMap<>();
    for (int i = 0; i < num.length; i++) {
      if (!hm.containsKey(num[i]))
        hm.put(num[i], 1);
      else
        hm.put(num[i], hm.get(num[i]) + 1);
      if (hm.size() == m) {
        Iterator<Integer> it = hm.keySet().iterator();// using iterator to modify the hashmap
        while (it.hasNext()) {
          int val = it.next();
          if (hm.get(val) == 1)
            it.remove();
          else
            hm.put(val, hm.get(val) - 1);
        }
      }
    }
    double thr = num.length / m;
    int[] ret = new int[m];
    int idx = 0;

    // clear cnt of all eles in hashmap to 0
    for (int key : hm.keySet())
      hm.put(key, 0);
    // recount
    for (int i = 0; i < num.length; i++) {
      if (hm.containsKey(num[i])) hm.put(num[i], hm.get(num[i]) + 1);
    }

    for (int key : hm.keySet())
      if (hm.get(key) > thr) ret[idx++] = key;
    System.out.println(Arrays.toString(ret));
    return ret;
  }
}
