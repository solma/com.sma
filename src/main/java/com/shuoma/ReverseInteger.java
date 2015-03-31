package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Arithmetic, source = LeetCode)
public class ReverseInteger {
  //second pass
  public int reverse(int x) {
    StringBuilder sb = new StringBuilder(String.valueOf(x));
    boolean neg = false;
    if (sb.charAt(0) == '-') {
      neg = true;
      sb.deleteCharAt(0);
    }
    sb.reverse();
    if (neg)
      return -Integer.parseInt(sb.toString());
    else
      return Integer.parseInt(sb.toString());
  }

  //first pass
  public int reverseFirstPass(int x) {
    StringBuilder sb = new StringBuilder(String.valueOf(x));
    boolean sign = false;
    if (sb.charAt(0) == '-') {
      sign = true;
      sb.deleteCharAt(0);
    }
    sb.reverse();
    int res = Integer.parseInt(sb.toString());
    if (sign)
      res *= -1;
    return res;
  }
}
