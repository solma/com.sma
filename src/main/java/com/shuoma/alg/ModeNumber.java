package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Reference.WeChat;
import static com.shuoma.util.CollectionsUtil.increaseMapCounter;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// find all numbers whose appearance takes at least 1/m of the population
// reference: wechat

@Tag(dss = {Array, Hash}, references = {LeetCode, WeChat})
public class ModeNumber {
  public static void main(String[] args) {
    System.out.println(new ModeNumber().modeNumber(new int[] {1, 3, 2, 2, 3, 2, 3, 1}, 3));
  }

  // linear time and constant space
  public List<Integer> modeNumber(int[] num, int m) {
    Map<Integer, Integer> hm = new HashMap<>();
    for (int i = 0; i < num.length; i++) {
      increaseMapCounter(hm, num[i], 1);
      if (hm.size() == m) {
        Iterator<Integer> it = hm.keySet().iterator();// using iterator to modify the hashmap
        while (it.hasNext()) {
          int val = it.next();
          if (hm.get(val) == 1) it.remove();
          else hm.put(val, hm.get(val) - 1);
        }
      }
    }

    // clear cnt of all eles in hashmap to 0
    for (int key : hm.keySet()) {
      hm.put(key, 0);
    }
    // recount
    for (int i = 0; i < num.length; i++) {
      if (hm.containsKey(num[i])) {
        hm.put(num[i], hm.get(num[i]) + 1);
      }
    }

    List<Integer> ret = new LinkedList<>();
    double thr = num.length / m;
    for (int key : hm.keySet()) {
      if (hm.get(key) > thr)
        ret.add(key);
    }
    return ret;
  }
}
