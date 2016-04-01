package com.sma.alg;

import java.util.ArrayList;

// find all n-digit numbers in which all n digits form an a monotonically increasing sequence

public class NumbersWithNIncreasingDigits {
  public static void main(String[] args) {
    System.out.println(numbersWithNIncreasingDigits(5));
  }

  public static ArrayList<String> numbersWithNIncreasingDigits(int n) {
    return numbersWithNIncreasingDigits(n, new StringBuilder(), 1);
  }

  public static ArrayList<String> numbersWithNIncreasingDigits(int n, StringBuilder sb, int sIdx) {
    ArrayList<String> ret = new ArrayList<String>();
    if (sb.length() == n) {
      ret.add(sb.toString());
      return ret;
    }
    if (sb.length() + 10 - sIdx >= n) {
      for (int i = sIdx + 1; i <= 9; i++) {
        sb.append(i);
        ret.addAll(numbersWithNIncreasingDigits(n, sb, i));
        sb.deleteCharAt(sb.length() - 1);
      }
    }
    return ret;
  }
}
