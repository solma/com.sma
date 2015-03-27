package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, source = LeetCode)
public class ExcelSheetColumnTitle {

  public String convertToTitle(int n) {
    if (n <= 0) {
      return null;
    }
    StringBuilder res = new StringBuilder();
    while (n > 0) {
      n--;
      res.insert(0, (char) ('A' + n % 26));
      n /= 26;
    }
    return res.toString();
  }
}
