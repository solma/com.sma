package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(dss = StringT, references = LeetCode)
public class LengthofLastWord {
  public int lengthOfLastWord(String s) {
    int end = s.length() - 1;
    while (end >= 0 && s.charAt(end) == ' ')
      end--;
    if (end == -1)
      return 0;
    int start = end;
    while (start >= 0 && s.charAt(start) != ' ')
      start--;
    return end - start;
  }
}
