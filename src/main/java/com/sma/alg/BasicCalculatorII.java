package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Calculator;
import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(dl = D2, dss = {Calculator, StackT}, references = LeetCode)
public class BasicCalculatorII {

  public static void main(String[] args) {
    String expr = "-22 + 1 - 3 * 2";
    System.out.println(new BasicCalculatorII().calculate(expr));
  }

  public int calculate(String s) {
    if (s == null) { return 0; }
    s = s.trim().replaceAll("\\s+", "");
    int length = s.length();

    int res = 0;
    long preVal = 0; // initial preVal is 0
    char sign = '+'; // initial sign is +
    int i = 0;
    while (i < length) {
      long curVal = 0;
      while (i < length && Character.isDigit(s.charAt(i))) { // int
        curVal = curVal * 10 + (s.charAt(i) - '0');
        i++;
      }
      if (sign == '+' || sign == '-') {
        res += preVal;  // update res
        preVal = curVal * (sign == '+' ? 1 : -1);
      } else if (sign == '*') {
        preVal *= curVal; // not update res, combine preVal & curVal and keep loop
      } else if (sign == '/') {
        preVal /= curVal; // not update res, combine preVal & curVal and keep loop
      }
      if (i < length) { // getting new sign
        sign = s.charAt(i);
        i++;
      }
    }
    res += preVal;
    return res;
  }
}
