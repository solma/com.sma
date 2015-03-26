package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Stack;

@Tag(dl = D2, dss = Stack, source = LeetCode)
public class EvaluateReversePolishNotation {
  public static void main(String[] args) {
    new EvaluateReversePolishNotation().main();
  }

  public void main() {
    String[] tokens = {"4", "-13", "5", "/", "+"};
    System.out.println(evalRPN(tokens));
  }

  public int evalRPN(String[] tokens) {
    int n = tokens.length;
    Stack<Integer> operands = new Stack<>();
    for (int i = 0; i < n; i++) {
      String c = tokens[i];
      if (isNumber(c))
        operands.push(Integer.parseInt(c));
      else {//operator
        int newer = operands.pop();
        int older = operands.pop();
        switch (c) {
          case "+":
            operands.push(older + newer);
            break;
          case "-":
            operands.push(older - newer);
            break;
          case "*":
            operands.push(older * newer);
            break;
          case "/":
            operands.push(older / newer);
            break;
          default:
            break;
        }
      }
    }
    if (operands.size() > 0) {
      return operands.pop();
    } else
      return 0;
  }

  boolean isNumber(String token) {
    try {
      Integer.parseInt(token);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }
}
