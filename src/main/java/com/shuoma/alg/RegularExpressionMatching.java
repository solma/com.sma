package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Recursion, dss = String, source = LeetCode)
public class RegularExpressionMatching {
  public boolean isMatch(String s, String p) {
    if (s == null)
      return p == null;
    return m(s, p, 0, 0);
  }

  public boolean m(String s, String p, int i, int j) {
    if (j == p.length())
      return i == s.length();
    // next char is not '*': must match current character
    if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
      if (i == s.length()) {
        return false;
      }
      return (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) && m(s, p, ++i, ++j);
    }
    // next char is '*'
    while (i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
      if (m(s, p, i++, j + 2)) {
        return true;
      }
    }
    return m(s, p, i, j + 2);
  }
}
