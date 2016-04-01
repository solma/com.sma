package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Calculator;
import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Difficulty.D2;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.Stack;

@Tag(dl = D2, dss = {Calculator, StackT, StringT}, references = LeetCode)
public class BasicCalculatorI {

  public static void main(String[] args) {
    String expr = "1 - (2 + 3 - ( 2 + 1) ) + 5";
    System.out.println(new BasicCalculatorI().calculate(expr));
  }

  int calculate(String s) {
    s = s.trim().replaceAll("\\s+", "");
    Stack<Integer> signStck = new Stack<>();
    signStck.push(1);  // initialize to avoid empty stack after first number
    signStck.push(1); // + for the first number
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
      System.out.println("i = " + i + " c = " + c + " res = " + res + " " + signStck);
    }
    return res;
  }
}
