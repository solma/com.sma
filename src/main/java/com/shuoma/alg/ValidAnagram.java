package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(dss = {Hash, StringT}, references = LeetCode)
public class ValidAnagram {
  public boolean isAnagram(String s, String t) {
    return sign(s).equals(sign(t));
  }

  Map<Character, Integer> sign(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      if (!map.containsKey(c)) map.put(c, 0);
      map.put(c, map.get(c) + 1);
    }
    return map;
  }
}
