package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = Greedy, dl = D2, dss = StringT, references = LeetCode)
public class IntegerToRoman {
  static int[] weights = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

  public static String intToRomanR(int num) {
    if (num == 0) return "";
    for (int i = 0; i < 13; i++) {
      if (num >= weights[i]) {
        return symbols[i] + intToRomanR(num - weights[i]);
      }
    }
    return "";
  }

  public static String intToRomanI(int num) {
    StringBuilder builder = new StringBuilder();
    int start = 0;
    while (num > 0) {
      for (int i = start; i < 13; i++) {
        if (num >= weights[i]) {
          num -= weights[i];
          builder.append(symbols[i]);
          break;
        }
        start = i + 1; // skip those impossible check, make it faster
      }
    }
    return builder.toString();
  }
}
