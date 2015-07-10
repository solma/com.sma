package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Backtracking;
import static com.shuoma.annotation.Tag.Algorithm.DynamicProgramming;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.google.common.collect.ImmutableSet;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// dynamic programming+dfs on dp results
// 1.dp for each position in s, find matching substring and record it in dp
//   eg. leetcode dict: leet, code, le, et, co, de
//        0 0 4 4  leet exists in dict, put 0 in position 5, le exists in dict, put 0 in position 3 etc.
// 2.dfs search for all possible segmentations in dp results. note that we want to start searching
//   from end of the dp list. it is more efficient. if (dp.get(s.length())==0)
//   we do not need to search. no segmentation is available


@Tag(algs = {Backtracking, DynamicProgramming}, dl = D3, dss = String, references = LeetCode)
public class WordBreakII {
  public static void main(String[] args) {
    //"leet", "code", "le", "et", "co", "de"
    Set<String> dict = ImmutableSet.of("b", "a");
    String inputString = "ab";

    for (String s : wordBreak(inputString, dict)) {
      System.out.println(s);
    }
  }

  public static List<String> wordBreak(String s, Set<String> dict) {
    List<List<Integer>> dp = new ArrayList<>();
    for (int i = 0; i < s.length() + 1; i++) {
      dp.add(new ArrayList<Integer>());
    }

    for (String e : dict) {
      System.out.print(e + "\t");
    }

    boolean outloopIsDict = true;
    if (outloopIsDict) {
      for (String e : dict) {
        for (int i = 1; i <= s.length(); i++) {
          int en = e.length();
          if (((i > en && !dp.get(i - en).isEmpty()) || (i == en)) && s.substring(i - en, i).equals(e)) {
            dp.get(i).add(i - en);
          }
        }
      }
    } else {
      for (int i = 1; i <= s.length(); i++) {
        for (String e : dict) {
          int en = e.length();
          if (((i > en && !dp.get(i - en).isEmpty()) || (i == en)) && s.substring(i - en, i)
              .equals(e)) {
            dp.get(i).add(i - en);
          }
        }
      }
    }

    for (List<Integer> prev : dp) {
      System.out.println(prev);
    }

    List<String> results = new ArrayList<>();
    StringBuilder re = new StringBuilder();
    helper(results, re, s, dp, s.length());
    return results;
  }

  public static void helper(List<String> results, StringBuilder re, String s, List<List<Integer>> dp, int cur) {
    if (cur == 0) {
      results.add(re.toString());
      return;
    }
    for (int p : dp.get(cur)) {
      if (cur < s.length())
        re.insert(0, " ");
      re.insert(0, s.substring(p, cur));
      helper(results, re, s, dp, p);
      re.delete(0, cur - p);
      if (cur < s.length())
        re.deleteCharAt(0);
    }
  }
}
