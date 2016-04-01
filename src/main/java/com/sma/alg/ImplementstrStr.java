package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(dss = StringT, references = LeetCode)
public class ImplementstrStr {
  public static void main(String[] args) {
    System.out.println(strStr(args[0], args[1]));
  }

  // O(nm)
  public static String strStr(String haystack, String needle) {
    assert (haystack != null && needle != null);
    int n = needle.length();
    if (n == 0)
      return haystack;

    int i = 0, j;
    while (i + n <= haystack.length()) {
      j = 0;
      if (haystack.charAt(i + j) == needle.charAt(j)) {
        while (j < n && haystack.charAt(i + j) == needle.charAt(j))
          j++;
        if (j == n)
          return haystack.substring(i);
      }
      i++;
    }
    return null;
  }
}
