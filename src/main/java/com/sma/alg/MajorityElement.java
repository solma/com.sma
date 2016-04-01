package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

@Tag(dss = Array, references = LeetCode)
public class MajorityElement {
  public int majorityElement(int[] num) {
    int n = num.length;
    int maj = num[0], cnt = 1;
    for (int i = 1; i < n; i++) {
      if (cnt == 0) {
        maj = num[i];
      }
      if (num[i] == maj) {
        cnt++;
      }
      else {
        cnt--;
      }
    }
    return maj;
  }
}
