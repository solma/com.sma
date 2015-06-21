package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.Stack;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.util.MathUtil.evaluateOperator;
import static com.shuoma.util.MathUtil.higherOrEqualPriority;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Tag(dl = D2, dss = {Stack, String}, reference = LeetCode) public class Calculator {

  public static void main(String[] args) {
    String expr = "(-22 + 1 - 3) + 2";
    new Calculator().main(expr);
  }

  public void main(String expression) {
    System.out.println(calculate(expression));
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

  // TLE
  public double calculate1(String expression) {
    List<String> rpn = toRPN(expression);
    //System.out.println(rpn);
    return evaluate(rpn);
  }

  double evaluate(List<String> rpnExpr) {
    Stack<Double> numStck = new Stack<>();
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

    StringBuilder operand = new StringBuilder();
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (Character.isDigit(c)) {
        operand.append(c);
      } else {// an operator
        // push the last operand and reset
        if (operand.length() > 0) {
          rpn.add(operand.toString());
          operand.setLength(0);
        }

        if (c != '(') {
          while (!operatorStck.isEmpty() && (c == ')' || higherOrEqualPriority(operatorStck.peek(),
              c))) {
            char popOut = operatorStck.pop();
            if (popOut == '(')
              break;
            rpn.add(popOut + "");
          }
        }
        if (c != ')')
          operatorStck.push(c);
      }
    }
    if (operand.length() > 0) {
      rpn.add(operand.toString()); // do not forget this line
    }

    while (!operatorStck.isEmpty())
      rpn.add(operatorStck.pop() + "");
    return rpn;
  }
}
