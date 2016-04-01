package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

/**
 Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 For example:
 Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 Follow up:
 Could you do it without any loop/recursion in O(1) runtime?
 */
@Tag(algs = Arithmetic, references = LeetCode)
public class AddDigit {

  int addDigits(int num) {
    if (num == 0) return 0;
    int r = num % 9;
    if (r == 0) return 9;
    return r;
  }

  int addDigitsNaive(int num) {
    assert(num >= 0);
    while (num > 9) {
      char[] ary = String.valueOf(num).toCharArray();
      num = 0;
      for (char c: ary) {
        num += c - '0';
      }
    }
    return num;
  }
}
