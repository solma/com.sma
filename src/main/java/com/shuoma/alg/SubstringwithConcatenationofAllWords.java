package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(dss = {HashTable, String}, reference = LeetCode)
public class SubstringwithConcatenationofAllWords {
  public List<Integer> findSubstring(String S, String[] L) {
    int len = L[0].length();
    int count = L.length;

    Map<String, Integer> words = new HashMap<>();
    for (String s : L) {
      if (words.containsKey(s)) {
        words.put(s, 1 + words.get(s));
      } else {
        words.put(s, 1);
      }
    }
    List<Integer> rv = new ArrayList<>();

    int slen = S.length();

    for (int i = 0; i <= slen - count * len; ) {//the first character of matching
      Map<String, Integer> targets = new HashMap<>(words);
      int forward = i;
      while (true) {
        String sub = S.substring(forward, forward + len);
        if (targets.containsKey(sub)) {
          if (targets.get(sub) == 1) {
            targets.remove(sub);
          } else {
            targets.put(sub, targets.get(sub) - 1);
          }
          if (targets.isEmpty()) {
            rv.add(i);
            break;
          }
          forward += len;
        } else {
          break;
        }
      }
      i++;
    }
    return rv;
  }
}
