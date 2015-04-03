package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Stack;

@Tag(dss = Stack, source = LeetCode)
public class LongestValidParenthesis {

  public String longestValidParentheses(String s) {
    int n = s.length();
    Stack<Integer> stck = new Stack<>();
    boolean[] keep = new boolean[n];
    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);
      if (c == '(') {
        stck.push(i);
      } else {
        if (stck.isEmpty()) {
          continue;
        }
        keep[i] = keep[stck.peek()] = true;
        stck.pop();
      }
    }

    StringBuilder res = new StringBuilder();
    for (int i = 0; i < n; i++) {
      res.append(keep[i] ? s.charAt(i) : "");
    }
    return res.toString();
  }

  //O(n)
  public int longestValidParentheses1(String s) {
    int n = s.length();
    int[] dp = new int[n];
    int max = 0;
    for (int i = n - 2; i >= 0; i--) {
      if (s.charAt(i) == '(') {
        int j = i + 1 + dp[i + 1];  //longest valid parenthesis starting from i+1
        if (j < n && s.charAt(j) == ')') {
          dp[i] = dp[i + 1] + 2;
          int k = 0;
          if (j + 1 < n) {  //add longest valid parenthesis starting from j+1
            k = dp[j + 1];
          }
          dp[i] += k;
        }
        max = Math.max(max, dp[i]);
      }
    }
    return max;
  }
}
