package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Calculator;
import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Difficulty.D2;

import com.sma.annotation.Tag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/** Linear equation solver. */
@Tag(dl = D2, dss = {Calculator, StackT, StringT})
public class LinearEquationSolver {

  private static final char CONSTANT_CHAR = '.';

  public static void main(String[] args) {
    LinearEquationSolver ins = new LinearEquationSolver("3a + ( 2c - 3 ) = 5b - ( a - c ) + 10");
    ins.solve(ins.provideSubstitution());
  }

  private Map<Character, Double> coefficients = new HashMap<>();
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
      // flip sign for right side terms
      if (isRightSide) {
        if (firstRightTerm) {
          firstRightTerm = false;
          if (!term.equals("-")) {
            terms.add("-");
          }
        }
        // flip sign
        if (term.equals("+") || term.equals("-")) {
          term = term.equals("+") ? "-" : "+";
        }
      }
      terms.add(term);
    }
    System.out.println(terms);
    return terms;
  }

  void reduceTerm(Map<Character, Double> coefficients, String term, boolean isAddition) {
    double coefficient;
    int n = term.length();
    char c = term.charAt(n - 1); // last char is the variable name

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

  void simplify(List<String> terms) {
    Stack<Boolean> operators = new Stack<>();
    operators.push(true);
    operators.push(true);
    for (String term : terms) {
      switch (term) {
        case "+" :
        case "(" :
          operators.push(operators.peek());
          break;
        case "-" :
          operators.push(!operators.peek());
          break;
        case ")" :
          operators.pop();
          break;
        default:
          reduceTerm(coefficients, term, operators.pop());
          break;
      }
    }
  }
}
