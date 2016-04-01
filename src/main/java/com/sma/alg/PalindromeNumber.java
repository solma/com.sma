package com.sma.alg;

import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(references = LeetCode)
public class PalindromeNumber {

  public boolean isPalindrome(int x) {
    if (x < 0) { return false; }
    int div = 1;
    while (x / div >= 10) { div *= 10; }
    while (x > 0) {
      int l = x / div;
      int r = x % 10;
      if (l != r) { return false; }
      x = (x % div) / 10;
      div /= 100;
    }
    return true;
  }
}
