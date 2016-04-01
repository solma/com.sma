package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;
import com.sma.util.ArrayUtil;

@Tag(dss = StringT, references = LeetCode)
public class ReverseWords {

  public String reverseWords(String s) {
    char[] arr = s.trim().replaceAll("\\s+", " ").toCharArray();
    ArrayUtil.reverse(arr);

    int l = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == ' ') {
        ArrayUtil.reverse(arr, l, i - 1);
        l = i + 1;
      }
    }
    ArrayUtil.reverse(arr, l, arr.length - 1);
    return new String(arr);
  }
}
