package com.shuoma.alg.string;

import com.shuoma.helper.CommonUtils;

import java.util.Stack;

public class ArithmeticExpressionEvaluation {

  public static void main(String[] args) {
    new ArithmeticExpressionEvaluation().main("(3*2)-(5/2)");
  }

  public void main(String expression) {
    String rpn = toRPN(expression);
    System.out.println(rpn);
    System.out.println(evaluate(rpn));
  }

  public String toRPN(String expression) {
    StringBuilder output = new StringBuilder();
    Stack<Character> operatorStck = new Stack<Character>();

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (CommonUtils.isNumber(c)) {
        output.append(c);
      } else {// an operator
        if (c != '(') {
          while (!operatorStck.isEmpty()
              && (c == ')' || CommonUtils.higherOrEqualPriority(operatorStck.peek(), c))) {
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

  public static double evaluate(String rpnExpr) {
    Stack<Double> numStck = new Stack<Double>();
    for (int i = 0; i < rpnExpr.length(); i++) {
      char c = rpnExpr.charAt(i);
      if (CommonUtils.isNumber(c)) {
        numStck.push(c - '0' + .0);
      } else {
        Double b = numStck.pop(), a = numStck.pop();
        numStck.push(CommonUtils.operator(a, b, c));
      }
    }
    return numStck.pop();
  }



}
