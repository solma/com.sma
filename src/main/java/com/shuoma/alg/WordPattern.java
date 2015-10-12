package com.shuoma.alg;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.Hash;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a substring in str.
 * <p/>
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
@Tag(algs = {Recursion}, dss = {Hash, StringT}, references = {LeetCode}) public class WordPattern {

  boolean wordPattern(String p, String s) {
    String[] values = s.split(" ");
    int pn = p.length();
    if (pn != values.length) {
      return false;
    }
    Map<Character, String> map = new HashMap<>();
    for (int i = 0; i < pn; i++) {
      char c = p.charAt(i);
      String v = values[i];
      if (map.containsKey(c)) {
        if (!map.get(c).equals(v)) {
          return false;
        }
      } else {
        if (map.values().contains(v)) {
          return false;
        }
        map.put(c, v);
      }
    }
    return true;
  }
}
