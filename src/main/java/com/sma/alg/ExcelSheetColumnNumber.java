package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(dl = D2, dss = Array, references = LeetCode)
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
