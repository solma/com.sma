package com.shuoma.util;

public class MathUtil {

  public static double evaluateOperator(double a, double b, char theta) {
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

  public static long factorial (int n) {
    long res = 1;
    for (; n > 1; n--) res *= n;
    return res;
  }

  public static int gcd(int n, int m) {
    return m == 0 ? n : gcd(m, n % m);
  }

  public static long greatestCommonDivisor(long a, long b) {
    return b == 0 ? a : greatestCommonDivisor(b, a%b);
  }

  /**
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
}
