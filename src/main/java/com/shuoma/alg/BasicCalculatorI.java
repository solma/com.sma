package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Stack;

@Tag(dl = D2, dss = {Stack, String}, references = LeetCode)
public class BasicCalculatorI {

  public static void main(String[] args) {
    String expr = "(-22 + 1 - 3) + 2";
    System.out.println(new BasicCalculatorI().calculate(expr));
  }

  public int calculate(String s) {
    s = s.trim().replaceAll("\\s+", "");
    Stack<Integer> signStck = new Stack<>();
    signStck.push(1);
    signStck.push(1);
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        int num = c - '0';
        int j = i + 1;
        while (j < s.length() && Character.isDigit(s.charAt(j))) {
          num = 10 * num + (s.charAt(j) - '0');
          j++;
        }
        res += signStck.pop() * num;
        i = j - 1;
      } else if (c == '+' || c == '(') {
        signStck.push(signStck.peek());
      } else if (c == '-') {
        signStck.push(-1 * signStck.peek());
      } else if (c == ')') {
        signStck.pop();
      }
      System.out.println("i = " + i + " " + signStck);
    }
    return res;
  }
}
