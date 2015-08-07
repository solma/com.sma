package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.Subarray;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.google.common.collect.ImmutableSet;

import com.shuoma.annotation.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * Abbreviation: e.g. "apple" can be abbreviated to 5, a4, 4e, a3e, ...
 * Given a target string , and a set of strings, return the minimal length of abbreviation
 * of this target string so that it won’t conflict with abbrs of the strings in the set.
 * For example: “apple”, [“blade”] -> a4 (5 is conflicted with “blade”),
 */
@Tag(algs = Recursion, dss = Subarray, references = Interview)
public class ShortestAbbreviation {

  public static void main(String[] args) {
    ShortestAbbreviation ins = new ShortestAbbreviation();
    Set<String> dict = ImmutableSet.of("plain", "amber", "blade", "spain", "loped", "hallo",
        "aplan", "aqewe");
    System.out.println(ins.shortestAbbreviation(dict, "apple"));
  }

  String shortestAbbreviation(Set<String> dict, String s) {
    if (s == null || s.isEmpty()) return "";
    int n = s.length();
    if (dict.isEmpty() || isLengthUnique(dict, s)) return n + "";
    if (dict.contains(s)) return s;

    String shortestAbbr = s;
    for (int i = 0; i < n; i++) {
      Set<String> prev = new HashSet<>(), aftr = new HashSet<>();
      for (String word : dict) {
        if (word.length() != n || word.charAt(i) != s.charAt(i)) continue;
        prev.add(word.substring(0, i));
        aftr.add(word.substring(i + 1));
      }
      String abbr = shortestAbbreviation(prev, s.substring(0, i)) + s.charAt(i)
          + shortestAbbreviation(aftr, s.substring(i + 1));
      if (abbr.length() < shortestAbbr.length()) {
        shortestAbbr = abbr;
      }
    }
    return shortestAbbr;
  }

  boolean isLengthUnique(Set<String> dict, String s) {
    for (String word : dict) {
      if (word.length() == s.length()) {
        return false;
      }
    }
    return true;
  }
}
