package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dss = String, source = LeetCode)
public class LongestCommonPrefix {
  public static void main(String[] args) {
    System.out
        .println(new LongestCommonPrefix().longestCommonPrefix(new String[] {"abc", "abaeq", "c"}));
  }

  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0)
      return "";
    if (strs.length == 1)
      return strs[0];

    StringSortByLength[] sortedStrs = new StringSortByLength[strs.length];
    for (int i = 0; i < strs.length; i++) {
      sortedStrs[i] = new StringSortByLength(strs[i]);
    }
    Arrays.sort(sortedStrs);

    for (int r = sortedStrs[0].s.length(); r >= 1; r--) {
      String prefix = sortedStrs[0].s.substring(0, r);
      boolean flag = true;

      for (int i = 1; i < sortedStrs.length; i++) {
        if (!sortedStrs[i].s.startsWith(prefix)) {
          flag = false;
          break;
        }
      }

      if (flag)
        return prefix;

    }
    return "";
  }
}


class StringSortByLength implements Comparable {
  String s;

  public StringSortByLength(String s) {
    this.s = s;
  }

  public int compareTo(Object other) {
    return s.length() - ((StringSortByLength) other).s.length();
  }
}
