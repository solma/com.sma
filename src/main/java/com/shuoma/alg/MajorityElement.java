package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Array;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dss = Array, reference = LeetCode)
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
