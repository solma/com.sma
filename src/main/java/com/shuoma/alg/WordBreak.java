package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.HashSet;
import java.util.Set;

@Tag(algs = {Backtracking, Recursion}, dl = D3, dss = String, source = LeetCode)
public class WordBreak {
  public static void main(String[] args) {
    new WordBreak().main();
  }

  public void main() {
    Set<String> dict = new HashSet<String>();
    dict.add("code");
    dict.add("leet");

    System.out.println(wordBreak1("leetcode", dict));
    //System.out.println(wordBreak("leetcode", dict));
  }

  public boolean wordBreak(String s, Set<String> dict) {
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 0; i < s.length(); i++) {
      if (!dp[i])
        continue;
      for (String e : dict) {
        int end = i + e.length();
        if (end > s.length())
          continue;
        String sub = s.substring(i, end);
        if (sub.equals(e))
          dp[end] = true;
      }
    }
    return dp[s.length()];
  }


  public boolean wordBreak1(String s, Set<String> dict) {
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 0; i < s.length(); i++) {
      if (!dp[i])
        continue;
      for (String e : dict) {
        int end = i + e.length();
        if (end > s.length())
          continue;
        String sub = s.substring(i, end);
        if (sub.equals(e))
          dp[end] = true;
      }
    }
    return dp[s.length()];
  }
}
