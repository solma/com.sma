package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = Array, reference = LeetCode)
public class ExcelSheetColumnNumber {

  public int titleToNumber(String s) {
    int num = 0;
    char[] arr = s.toCharArray();
    for (int i = 0; i < arr.length; i++) {
      num += (arr[i] - 'A' + 1) * Math.pow(26, arr.length - 1 - i);
    }
    return num;
  }
}
