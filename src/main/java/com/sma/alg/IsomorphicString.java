package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

@Tag(dss = {StringT, Hash}, references = LeetCode)
public class IsomorphicString {

  public boolean isIsomorphic(String s, String t) {
    return isIsomorphicOrder(s, t) && isIsomorphicOrder(t, s);
  }

  boolean isIsomorphicOrder(String s, String t) {
    int sLen = s.length(), tLen = t.length();
    if (sLen != tLen) return false;

    Map<Character, Character> mapping = new HashMap<>();
    for (int i = 0; i < sLen; i++) {
      char sC = s.charAt(i);
      char tC = t.charAt(i);
      if (mapping.containsKey(sC) && tC != mapping.get(sC)) {
        return false;
      } else {
        mapping.put(sC, tC);
      }
    }
    return true;
  }
}
