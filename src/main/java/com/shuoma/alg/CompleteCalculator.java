package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.util.MathUtil.evaluateOperator;
import static com.shuoma.util.MathUtil.lowerOrEqualPriority;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Tag(dl = D2, dss = {Stack})
public class CompleteCalculator {
  public static void main(String[] args) {
    String expr = "22 - (1 + 3 * -2)";
    System.out.println(new CompleteCalculator().calculate(expr));
  }

  public double calculate(String expression) {
    List<String> rpn = toRPN(expression);
    System.out.println(rpn);
    return evaluate(rpn);
  }

  double evaluate(List<String> rpnExpr) {
    java.util.Stack<Double> numStck = new Stack<>();
    for (int i = 0; i < rpnExpr.size(); i++) {
      String ele = rpnExpr.get(i);
      if (ele.length() == 1 && !Character.isDigit(ele.charAt(0))) {
        Double b = numStck.pop(), a = numStck.pop();
        numStck.push(evaluateOperator(a, b, ele.charAt(0)));
      } else {
        numStck.push(Double.parseDouble(ele));
      }
    }
    return numStck.pop();
  }

  List<String> toRPN(String expression) {
    expression = expression.replaceAll("\\s+", "");
    List<String> rpn = new LinkedList<>();
    Stack<Character> operatorStck = new Stack<>();

    int n = expression.length();
    boolean negative = false;
    for (int i = 0; i < n; ) {
      char c = expression.charAt(i);
      if (Character.isDigit(c)) {
        long curVal = 0;
        while (i < n && Character.isDigit(expression.charAt(i))) { // int
          curVal = curVal * 10 + (expression.charAt(i) - '0');
          i++;
        }
        curVal *= negative ? -1 : 1;
        negative = false;
        rpn.add(String.valueOf(curVal));
      } else {
        // special dealing with negative sign
        if (c == '-' && (i == 0 || !Character.isDigit(expression.charAt(i - 1)))) {
          negative = true;
        } else {
          if (c != '(') {
            while (!operatorStck.isEmpty() && (c == ')' || lowerOrEqualPriority(c, operatorStck.peek()))) {
              char popOut = operatorStck.pop();
              if (popOut == '(') {
                break;
              }
              rpn.add(String.valueOf(popOut));
            }
          }
          if (c != ')') {
            operatorStck.push(c);
          }
        }
        i++;
      }
      System.out.println(c + " " + rpn + " " + operatorStck);
    }
    while (!operatorStck.isEmpty()) {
      rpn.add(operatorStck.pop() + "");
    }
    return rpn;
  }
}
