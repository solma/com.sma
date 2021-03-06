package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;
import static com.sma.annotation.Tag.Complexity.Quadratic;

import com.sma.annotation.Tag;

@Tag(algs = DynamicProgramming, dss = StringT, timecomplexity = Quadratic, references = LeetCode)
/**
 Given a string S and a string T, count the number of distinct subsequences of S that equal to T.
 Here is an example:
 S = "rabbbit", T = "rabbit"
 Return 3.
 */
public class DistinctSubsequences {

  public static void main(String[] args) {
    DistinctSubsequences ins = new DistinctSubsequences();
    System.out.println(ins.numDistinct("rabbbit", "rabbit"));
    //System.out.println(ins.numDistinct("rabbit", "rabbbit"));
  }

  public int numDistinct(String S, String T) {
    int[] occurrence = new int[T.length() + 1];
    occurrence[0] = 1;
    //o[i, j]=o[i-1, j]+ (o[i-1, j-1] if S[i]==T[j]);
    for (int i = 0; i < S.length(); i++) { //i 0-base index
      for (int j = T.length(); j >= 1; j--)//j 1-base index
        if (S.charAt(i) == T.charAt(j - 1)) {
          occurrence[j] += occurrence[j - 1];
        }
    }
    return occurrence[T.length()];
  }
}
