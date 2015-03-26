package com.shuoma;

import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(algs = DynamicProgramming, dss = String, source = LeetCode)
public class DistinctSubsequences {

  public int numDistinct(String S, String T) {
    int[] occurence = new int[T.length() + 1];
    occurence[0] = 1;
    //o[i, j]=o[i-1, j]+(S[i]==T[j])*o[i-1, j-1];
    for (int i = 0; i < S.length(); i++) {
      for (int j = T.length(); j >= 1; j--)
        if (S.charAt(i) == T.charAt(j - 1)) {
          occurence[j] += occurence[j - 1];
        }
    }
    return occurence[T.length()];
  }
}
