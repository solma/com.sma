package com.shuoma;


import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.Source.Leetcode;

import com.shuoma.annotation.Tag;

import java.util.Stack;

@Tag(ds = Stack, source = Leetcode)
public class LongestValidParenthesis {
  public static void main(String[] args) {
    LongestValidParenthesis ins = new LongestValidParenthesis();
    System.out.println(ins.longestValidParentheses1(")()())"));
    System.out.println(ins.longestValidParenthese(")()())"));
  }

  public String longestValidParenthese(String s) {
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

  //O(n^2) TLE
  public int longestValidParentheses(String s) {
    int n = s.length();
    boolean[][] valid = new boolean[n + 1][n + 1];//inclusive-start-exclusive-end
    int maxLen = 0;
    for (int len = 2; len <= n; len++) {
      for (int i = 0; i < n + 1 - len; i++) {
        int j = i + len;
        if (s.charAt(i) == '(' && s.charAt(j - 1) == ')' && (len == 2 || valid[i + 1][j - 1])) {
          valid[i][j] = true;
          maxLen = Math.max(maxLen, len);
        } else {
          for (int k = i + 1; k < j - 2; k++) {
            if (valid[i][k + 1] && valid[k + 1][j]) {
              valid[i][j] = true;
              maxLen = Math.max(maxLen, len);
              break;
            }
          }
        }
      }
    }
    return maxLen;
  }


}
