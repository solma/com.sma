package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D3, dss = {HashTable, String}, source = LeetCode)
public class MinimumWindowSubstring {
  public static void main(String[] args) {
    System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
  }

  public String minWindow(String S, String T) {
    int[] needToFind = new int[256], hasFound = new int[256];
    int nT = T.length(), nS = S.length();
    if (nS < nT)
      return "";
    for (int i = 0; i < nT; i++) {
      needToFind[(int) T.charAt(i)]++;
    }
    int end, cnt = 0, begin = 0, min = Integer.MAX_VALUE;
    int minL = 0, minR = -1;
    for (end = 0; end < nS; end++) {
      if (needToFind[(int) S.charAt(end)] == 0)
        continue;
      hasFound[(int) S.charAt(end)]++;
      if (hasFound[(int) S.charAt(end)] <= needToFind[(int) S.charAt(end)])
        cnt++;

      if (cnt == nT) {
        //System.out.println(S.charAt(end)+" "+needToFind[(int)S.charAt(end)]);
        while (needToFind[(int) S.charAt(begin)] == 0
            || hasFound[(int) S.charAt(begin)] > needToFind[(int) S.charAt(begin)]) {
          if (hasFound[(int) S.charAt(begin)] > needToFind[(int) S.charAt(begin)])
            hasFound[(int) S.charAt(begin)]--;
          begin++;
        }
        if (end - begin < min) {
          min = end - begin;
          minL = begin;
          minR = end;
        }
      }
    }
    if (minL <= minR)
      return S.substring(minL, minR + 1);
    else
      return "";
  }
}
