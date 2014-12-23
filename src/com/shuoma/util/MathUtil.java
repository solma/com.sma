package com.shuoma.util;

public class MathUtil {

  public static long greatestCommonDivisor(long a, long b) {
    return b == 0 ? a : greatestCommonDivisor(b, a%b);
  }

  /**
   *
   * @param newChar
   * @param stackTop
   * @return newChar >= stackTop
   */
  public static boolean higherOrEqualPriority(char newChar, char stackTop) {
    switch (newChar) {
      case '+':
      case '-':
        if (stackTop == '+' || stackTop == '-' || stackTop == '=')
          return true;
        else
          return false;
      case '*':
      case '/':
        if (stackTop == '(' || stackTop == 's' || stackTop == 'c' || stackTop == 'l'
            || stackTop == '^')
          return false;
        else
          return true;
      case '=':
        if (stackTop == '=')
          return true;
        else
          return false;
      case 's':
      case 'c':
      case 'l':
      case '^':
        if (stackTop == '(')
          return false;
        else
          return true;
      default:
        return false;
    }
  }

  public static boolean isNumber(char c) {
    int diff = c - '0';
    if (diff >= 0 && diff <= 9)
      return true;
    else
      return false;
  }

  public static double operator(double a, double b, char theta) {
    switch (theta) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        return a / b;
      case '^':
        return Math.pow(a, b);
    }
    return 0;
  }
}
