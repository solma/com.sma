package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.util.ArrayUtil;

@Tag(dss = String, source = LeetCode)
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
