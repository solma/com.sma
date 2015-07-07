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
   * @return newChar <= stackTop
   */
  // https://en.wikipedia.org/wiki/Shunting-yard_algorithm
  public static boolean lowerOrEqualPriority(char newChar, char stackTop) {
    switch (newChar) {
      case '=':
        return true;
      case '+':
      case '-':
        return !(stackTop == '=' || stackTop == '(');
      case '*':
      case '/':
        return !(stackTop == '+' || stackTop == '-' || stackTop == '=' || stackTop == '(');
      case 's':
      case 'c':
      case 'l':
      case '^':
        return stackTop == 's' || stackTop == 'c' || stackTop == 'l';
      default:
        throw new IllegalArgumentException("No such character");
    }
  }
}
