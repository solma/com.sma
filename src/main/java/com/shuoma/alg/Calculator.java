package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D2;

import com.shuoma.annotation.Tag;
import com.shuoma.util.MathUtil;

import java.util.Stack;

@Tag(dl = D2, dss = {Stack, String})
public class Calculator {

  public static void main(String[] args) {
    new Calculator().main("(3*2)-(5/2)");
  }

  public void main(String expression) {
    String rpn = toRPN(expression);
    System.out.println(rpn);
    System.out.println(evaluate(rpn));
  }

  public static double evaluate(String rpnExpr) {
    Stack<Double> numStck = new Stack<>();
    for (int i = 0; i < rpnExpr.length(); i++) {
      char c = rpnExpr.charAt(i);
      if (MathUtil.isNumber(c)) {
        numStck.push(c - '0' + .0);
      } else {
        Double b = numStck.pop(), a = numStck.pop();
        numStck.push(MathUtil.operator(a, b, c));
      }
    }
    return numStck.pop();
  }

  public String toRPN(String expression) {
    StringBuilder output = new StringBuilder();
    Stack<Character> operatorStck = new Stack<>();

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (MathUtil.isNumber(c)) {
        output.append(c);
      } else {// an operator
        if (c != '(') {
          while (!operatorStck.isEmpty()
              && (c == ')' || MathUtil.higherOrEqualPriority(operatorStck.peek(), c))) {
            char popOut = operatorStck.pop();
            if (popOut == '(') break;
            output.append(popOut);
          }
        }
        if (c != ')') operatorStck.push(c);
      }
    }

    while (!operatorStck.isEmpty())
      output.append(operatorStck.pop());
    return output.toString();
  }
}
