package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(dss = {Hash, StringT}, references = LeetCode)
public class SubstringwithConcatenationofAllWords {
  public List<Integer> findSubstring(String S, String[] L) {
    int len = L[0].length();
    int count = L.length;

    Map<String, Integer> wordCounters = new HashMap<>();
    for (String s : L) {
      if (wordCounters.containsKey(s)) {
        wordCounters.put(s, 1 + wordCounters.get(s));
      } else {
        wordCounters.put(s, 1);
      }
    }
    List<Integer> rv = new ArrayList<>();

    int slen = S.length();

    for (int i = 0; i <= slen - count * len; i++) {//the first character of matching
      Map<String, Integer> targets = new HashMap<>(wordCounters);
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
    }
    return rv;
  }
}
