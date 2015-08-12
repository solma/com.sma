package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 match pattern string p with string s. E.g.
 p="abba", s="redbluebluered", true
 p="aaaa", s="redredredred", true
 p="abab", s="redbluebluered", false
 p="abba", s="redredredred", false
 */
@Tag(algs = {Recursion}, dss = {StringT}, references = Interview)
public class PatternMatch {

  boolean match(String p, String s) {
    return match(p, 0, s, 0, new HashMap<Character, String>(), new HashMap<String, Character>());
  }

  boolean match(String p, int pIdx, String s, int sIdx,
      Map<Character, String> mapping, Map<String, Character> invertedMapping) {
//    System.out.println(pIdx + " " + sIdx + " " + mem);
    int sn = s.length();
    if (pIdx == p.length()) return true;
    if (sIdx >= sn) return false;
    char c = p.charAt(pIdx);
    String mapped;
    if (mapping.containsKey(c)) {
      mapped = mapping.get(c);
      int n = mapped.length();
      if (sIdx + n > sn || !s.substring(sIdx, sIdx + n).equals(mapped)) {
        return false;
      }
      return match(p, pIdx + 1, s, sIdx + n, mapping, invertedMapping);
    }

    for (int idx = sIdx + 1; idx < sn; idx++) {
      c = p.charAt(pIdx);
      mapped = s.substring(sIdx, idx);
      if (invertedMapping.containsKey(mapped)) { // diff chars cannot map to same string
        continue;
      }
      mapping.put(c, mapped);
      invertedMapping.put(mapped, c);
      if (match(p, pIdx + 1, s, idx, mapping, invertedMapping)) {
        return true;
      } else {
        invertedMapping.remove(mapped);
        mapping.remove(c);
      }
    }
    return false;
  }
}
