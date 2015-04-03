package com.shuoma.alg;
// Given two strings S and T, determine if they are both one edit distance apart.
// O(n) time, O(1) space

import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D3, dss = String, source = LeetCode)
public class OneEditDistance {
  public static void main(String[] args) {
    System.out.println(isOneEditDistance("aee", "ass"));
  }

  public static boolean isOneEditDistance(String s, String t){
    int sl = s.length(), tl = t.length();
    if (sl > tl) return isOneEditDistance(t, s);
    int diffLen = tl - sl;
    if (diffLen > 1) return false;
    int i = 0;
    while (i < sl && s.charAt(i) == t.charAt(i)) {
      i++;
    }
    if (i == sl) {
      return diffLen > 0;
    }
    if (diffLen == 0) {
      i++; // modify it
    }
    while (i < sl && s.charAt(i) == t.charAt(i + diffLen)) {
      i++;
    }
    return i == sl;
  }
}
