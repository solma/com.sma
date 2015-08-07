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

// Given an array of size N, find all numbers whose appear more than n/m times
// reference: wechat

@Tag(dss = {Array, Hash}, references = {LeetCode, WeChat})
public class ModeNumber {
  public static void main(String[] args) {
    ModeNumber ins = new ModeNumber();
    System.out.println(ins.modeNumber(new int[] {1, 3, 2, 2, 3, 2, 3, 1}, 3));
    System.out.println(ins.moreThanHalf(new int[] {2, 2, 1, 3, 3, 2, 2, 1}));
  }

  // special case when m = 2;
  int moreThanHalf(int[] num) {
    int cnt = 0;
    int ret = -1;
    for (int i = 0; i < num.length; i++) {
      if (cnt == 0 || num[i] == num[i - 1]) {
        cnt++;
      } else {
        cnt--;
      }
      if (cnt > 0) {
        ret = num[i];
      }
    }
    return ret;
  }


  // linear time and constant space
  List<Integer> modeNumber(int[] num, int m) {
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
