package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dl = D3, dss = StringT, references = LeetCode)
public class WildcardMatching {

  public boolean isMatch(String s, String p) {
    if (s == null || p == null)
      return false;

    // calculate count for non-wildcard char
    int count = 0;
    for (char c : p.toCharArray()) {
      if (c != '*')
        ++count;
    }
    // the count should not be larger than that of s
    if (count > s.length())
      return false;

    boolean[] matches = new boolean[s.length() + 1]; //Note the length is s.length()+1
    matches[0] = true;
    int pid = 0, firstMatch = 0;
    while (pid < p.length()) {
      // skip duplicate '*'
      if (pid > 0 && p.charAt(pid) == '*' && p.charAt(pid - 1) == '*') {
        ++pid;
        continue;
      }

      // if '*', fill up the rest of row
      if (p.charAt(pid) == '*') {
        // fill up the rest of row with true, up to the first match in previous row
        for (int i = s.length(); i > firstMatch; i--)
          matches[i] = true;
      } else {
        // fill up backwards:
        // - set to true if match current char and previous diagnal also match
        // - otherwise, set to false
        int match = -1;
        for (int i = s.length(); i > firstMatch; i--) {
          matches[i] = matches[i - 1] & (p.charAt(pid) == s.charAt(i - 1) || p.charAt(pid) == '?');
          if (matches[i])
            match = i;
        }
        if (match < 0) {
          return false;
        }
        firstMatch = match;
      }
//      System.out.println(
//          "pi=" + pid + " " + p.charAt(pid) + " " + " firstMatch=" + firstMatch + "  " + Arrays
//              .toString(matches));

      ++pid;
    }

    return matches[s.length()];
  }


  //recursion TLE
  public boolean isMatchRecursion(String s, String p) {
    return isMatch(s, p, 0, 0);
  }

  public boolean isMatch(String s, String p, int sIdx, int pIdx) {
    if (sIdx == s.length() || pIdx == p.length()) {
      if (sIdx == s.length()) {
        while (pIdx < p.length() && p.charAt(pIdx) == '*')
          pIdx++;
        if (pIdx == p.length())
          return true;
        else
          return false;
      } else
        return false;
    }
    char y = p.charAt(pIdx);
    // System.out.println(s+" "+p+" "+y+" ");
    if (y != '*') {
      if (y != '?') {
        char x = s.charAt(sIdx);
        if (x != y)
          return false;
      }
      return isMatch(s, p, sIdx + 1, pIdx + 1);
    } else {
      for (int i = sIdx; i <= s.length(); i++) {
        if (isMatch(s, p, i, pIdx + 1))
          return true;
      }
      return false;
    }
  }

}


