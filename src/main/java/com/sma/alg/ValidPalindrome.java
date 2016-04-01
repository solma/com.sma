package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.RegularExpression;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(algs = RegularExpression, dss = StringT, references = LeetCode)
public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    if (s == null)
      return false;
    if (s.length() == 0)
      return true;
    //catch how to use regex and string is immutatble
    s = s.replaceAll("[[^\\d]&&[^a-zA-Z]]", "");
    int i = 0, j = s.length() - 1;
    s = s.toLowerCase();
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) { return false; }
      i++;
      j--;
    }
    return true;
  }
}
