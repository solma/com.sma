package com.sma.alg;

import com.sma.annotation.Tag;

import static com.sma.annotation.Tag.Algorithm.SlidingWindow;
import static com.sma.annotation.Tag.DataStructure.Hash;
import static com.sma.annotation.Tag.DataStructure.Substring;
import static com.sma.annotation.Tag.Difficulty.D3;
import static com.sma.annotation.Tag.Reference.LeetCode;

/**
 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".
 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
@Tag(algs = SlidingWindow, dl = D3, dss = {Hash, Substring}, references = LeetCode)
public class MinimumWindowSubstring {
  public static void main(String[] args) {
    System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
  }

  public String minWindow(String S, String T) {
    int[] needToFind = new int[256], hasFound = new int[256];
    int nT = T.length(), nS = S.length();
    if (nS < nT) { return ""; }
    for (int i = 0; i < nT; i++) {
      needToFind[T.charAt(i)]++;
    }
    int end, cnt = 0, begin = 0, min = Integer.MAX_VALUE;
    int minL = 0, minR = -1;
    for (end = 0; end < nS; end++) {
      if (needToFind[S.charAt(end)] == 0) { continue; }
      hasFound[S.charAt(end)]++;
      if (hasFound[S.charAt(end)] <= needToFind[S.charAt(end)]) {
        cnt++;
      }

      if (cnt == nT) {
        //System.out.println(S.charAt(end)+" "+needToFind[(int)S.charAt(end)]);
        while (needToFind[S.charAt(begin)] == 0
            || hasFound[S.charAt(begin)] > needToFind[S.charAt(begin)]) {
          if (hasFound[S.charAt(begin)] > needToFind[S.charAt(begin)]) {
            hasFound[S.charAt(begin)]--;
          }
          begin++;
        }

        if (end - begin < min) {
          min = end - begin;
          minL = begin;
          minR = end;
        }
      }
    }
    return minL <= minR ? S.substring(minL, minR + 1) : "";
  }
}
