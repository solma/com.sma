package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.Stack;

@Tag(dss = StackT, references = Interview)
public class LongestValidParenthesisSubsequence {
  public String longestValidParentheses(String s) {
    int n = s.length();
    java.util.Stack<Integer> stck = new Stack<>();
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
}
