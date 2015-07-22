package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Trick.AccumulativeSum;
import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.DataStructure.Hash;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string of 0's and 1's find the longest substring that contains equal number 0's and 1's
 */
@Tag(dss = {Array, Hash}, tricks = AccumulativeSum)
public class LongestBalancedSubstring {
  public static void main(String[] args) {
    System.out.println(longestBalancedSubstring(args[0]));
  }

  public static String longestBalancedSubstring(String str) {
    char[] arr = str.toCharArray();
    int n = arr.length;

    // accumulative sum
    int[] cnt = new int[n + 1];
    for (int i = 0; i < n; i++) {
      if (arr[i] == '1') {
        cnt[i + 1] = cnt[i] + 1;
      } else {
        cnt[i + 1] = cnt[i] - 1;
      }
    }

    HashMap<Integer, List<Integer>> cntMap = new HashMap<>();
    for (int i = 0; i <= n; i++) { // start from 0
      if (!cntMap.containsKey(cnt[i])) {
        cntMap.put(cnt[i], new ArrayList<Integer>());
      }
      cntMap.get(cnt[i]).add(i - 1);
    }

    int maxLen = 0, s = 0, e = 0;
    for (List<Integer> li : cntMap.values()) {
      int diff = li.get(li.size() - 1) - li.get(0);
      if (diff > maxLen) {
        maxLen = diff;
        s = li.get(0);
        e = li.get(li.size() - 1);
      }
    }
    return maxLen == 0 ? null : str.substring(s + 1, e + 1); // s+1, e+1
  }
}
