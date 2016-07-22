package com.sma.alg;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import static com.sma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.sma.annotation.Tag.Complexity.Quadratic;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

/**
 Given a string s, partition s such that every substring of the partition is a palindrome.
 Return the minimum cuts needed for a palindrome partitioning of s.

 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
@Tag(algs = DynamicProgramming, timecomplexity = Quadratic, dss = StringT, references = LeetCode)
public class PalindromePartitionII {
  public static void main(String[] args) {
    //"aoecddceoaaeqeeqe"
    System.out.println(new PalindromePartitionII().minCut("aoecddceoabceqeeqe"));
  }

  public int minCut(String s) {
    int n = s.length();
    if (n == 1) { return 0; }

    int[] dp = new int[n];
    // dp[i]: min cut for s.substring(i)
    for (int i = 0; i < n; i++) {
      dp[i] = n - 1 - i;
    }

    // for palindrome parlin[i][j] = f(parlin[i+1][j-1])
    // for min palindrome parlin[i] = Min k>i (parlin(k)+1)
    //  ---|----|-------
    //  0  i    j   //i inclusive j inclusive for palindrome
    boolean[][] parlin = new boolean[n][n];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        if (s.charAt(i) == s.charAt(j) && (i + 1 >= j - 1 || parlin[i + 1][j - 1])) {
          parlin[i][j] = true;
          dp[i] = j == n - 1 ? 0 : Math.min(dp[i], dp[j + 1] + 1);
        }
        //need this line when change parlin to 1-d
        //else parlin[j] = false;
      }
    }
    return dp[0];
  }


  //TLE
  public void minCut1(int l, int e, List<Integer> cuts, int[][] firstCutofMinCut) {
    int cut;
    if (l < e && firstCutofMinCut[l][e] >= 0) {
      cut = firstCutofMinCut[l][e];
      cuts.add(cut);
      //System.out.println(l+" "+e+"  "+cut);
      //minCut(0, cut, cuts, firstCutofMinCut);//logic will be incorrect with this line
      minCut1(cut + 1, e, cuts, firstCutofMinCut);
    }
  }

  public int minCut1(String s) {
    if (s == null || s.length() == 0)
      return -1;
    int sLen = s.length();
    boolean[][] isPalindrome = new boolean[sLen][sLen];
    int i, len, j, k;
    for (i = 0; i < sLen; i++)
      isPalindrome[i][i] = true;
    int[][] minCut = new int[sLen][sLen];
    int[][] firstCutofMinCut = new int[sLen][sLen];

    for (i = 0; i < sLen; i++)
      for (j = 0; j < sLen; j++) {
        if (i == j)
          minCut[i][j] = 0;
        else
          minCut[i][j] = Integer.MAX_VALUE;
        firstCutofMinCut[i][j] = -1;
      }

    boolean earlyEnd = false;
    for (len = 1; len <= sLen; len++)
      for (i = 0; i <= sLen - len; i++) {
        j = i + len - 1;
        //                System.out.println(i+" "+j+" "+len);
        if ((i + 1 > j - 1 || isPalindrome[i + 1][j - 1]) && s.charAt(i) == s.charAt(j)) {
          isPalindrome[i][j] = true;
          minCut[i][j] = 0;
        }
        //if(minCut[0][sLen-1]==0) {earlyEnd=true; break;}
        for (k = i; k < j; k++) {
          if (minCut[i][k] + minCut[k + 1][j] + 1 < minCut[i][j]) {
            minCut[i][j] = minCut[i][k] + minCut[k + 1][j] + 1;
            firstCutofMinCut[i][j] = k;
          }
        }
      }

    List<Integer> cuts = new ArrayList<>();
    minCut1(0, sLen - 1, cuts, firstCutofMinCut);
    System.out.println(cuts);
    return minCut[0][sLen - 1];
  }
}
