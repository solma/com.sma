package com.shuoma.alg.math;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/** Linear equation solver. */
public class LinearEquationSolver {

  private static final char CONSTANT_CHAR = '.';

  public static void main(String[] args) {
    LinearEquationSolver ins = new LinearEquationSolver("3a + ( 2c - 3 ) = 5b + ( a - c ) + 10");
    ins.solve(ins.provideSubstitution());
  }

  private Map<Character, Double> coefficients;
  private String expression;

  public LinearEquationSolver(String expression) {
    this.expression = expression;
    simplify(normalizeExpression());
  }

  public Map<Character, Double> provideSubstitution() {
    Map<Character, Double> substitutions = new HashMap<>();
    substitutions.put('a', 3.);
    substitutions.put('c', 1.5);
    return substitutions;
  }

  public double solve(Map<Character, Double> substitutions) {
    System.out.println(coefficients);
    double sum = 0;
    char variable = CONSTANT_CHAR;
    for (char c : coefficients.keySet()) {
      if (c == CONSTANT_CHAR) {
        continue;
      }
      if (!substitutions.containsKey(c)) {
        variable = c;
        continue;
      }
      sum += substitutions.get(c) * coefficients.get(c);
    }
    sum += coefficients.get(CONSTANT_CHAR);
    double solution = -sum / coefficients.get(variable);
    System.out.println(variable + " = " + solution);
    return solution;
  }

  List<String> normalizeExpression() {
    // move the right side of "=" to the left side
    List<String> terms = new LinkedList<>();
    boolean isRightSide = false, firstRightTerm = false;
    for (String term : expression.split(" ")) {
      if (term.equals("=")) {
        isRightSide = true;
        firstRightTerm = true;
        continue;
      }
      if (isRightSide) {
        if (firstRightTerm) {
          firstRightTerm = false;
          if (!term.equals("-")) {
            terms.add("-");
          }
        }
        if (term.equals("+") || term.equals("-")) {
          term = term.equals("+") ? "-" : "+";
        }
      }
      terms.add(term);
    }
    System.out.println(terms);
    return terms;
  }

  void parseTerm(Map<Character, Double> coefficients, String term, boolean isAddition) {
    double coefficient;
    int n = term.length();
    char c = term.charAt(n - 1);

    int sign = isAddition ? 1 : -1;
    if (c >= 'a' && c <= 'z') {
      if (n == 1) {
        coefficient = 1;
      } else {
        coefficient = Double.parseDouble(term.substring(0, n - 1));
      }
    } else { // constant term
      c = CONSTANT_CHAR;
      coefficient = Double.parseDouble(term);
    }

    if (!coefficients.containsKey(c)) {
      coefficients.put(c, .0);
    }
    coefficients.put(c, coefficients.get(c) + sign * coefficient);
  }

  void reduce(Map<Character, Double> outParenthesisStack,
      Map<Character, Double> inParenthesisStack, boolean isAddition) {
    int sign = isAddition ? 1 : -1;
    for (Character c : outParenthesisStack.keySet()) {
      double base = 0;
      if (inParenthesisStack.containsKey(c)) {
        base += inParenthesisStack.get(c);
      }
      inParenthesisStack.put(c, sign * base + outParenthesisStack.get(c));
    }
  }

  void simplify(List<String> terms) {
    Stack<Boolean> operators = new Stack<>();
    Stack<Map<Character, Double>> coefficientsStack = new Stack<>();
    Map<Character, Double> tempCoefficients = new HashMap<>();
    boolean isAddition = true; // default if no sign then first term is positive
    for (String term : terms) {
      switch (term) {
        case "+" :
          isAddition = true;
          break;
        case "-" :
          isAddition = false;
          break;
        case "(" :
          coefficientsStack.push(tempCoefficients);
          operators.push(isAddition);
          tempCoefficients = new HashMap<>();
          isAddition = true; // reinitialize
          break;
        case ")" :
          reduce(coefficientsStack.pop(), tempCoefficients, operators.pop());
          break;
        default:
          parseTerm(tempCoefficients, term, isAddition);
          break;
      }
    }
    coefficients = tempCoefficients;
  }
}
