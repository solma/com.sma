package com.shuoma.alg.math;

import com.shuoma.helper.MathUtil;

/**
 * Abstraction of fraction number that can be arbitrary precise.
 */
public class Fraction {

  public static final char DELIMETER_CHARACTER = '.';
  public static final char ESCAPE_CHARACTER = '\\';

  /** String representation of the fraction. Repeating decimals are preceded by a dot. */
  String decimals;

  public Fraction(String value) {
    this.decimals = value;
  }

  /** Return the simple form of the fraction. */
  public String toSimpleFraction() {
    String[] fields = decimals.split(ESCAPE_CHARACTER + String.valueOf(DELIMETER_CHARACTER));

    SimpleFraction integerPart =
        new SimpleFraction(Long.parseLong(fields[0]), 1);

    SimpleFraction nonRepeatingDecimals =
        new SimpleFraction(fields[1].equals("") ? 0 : Long.parseLong(fields[1]),
            (long) Math.pow(10, fields[1].length()));

    SimpleFraction repeatingDecimals = new SimpleFraction(0, 1);
    if (fields.length ==3 && fields[2].length() > 0) {
      long exp;
      for (int i = 0; i < fields[2].length(); i++) {
        repeatingDecimals.add(new SimpleFraction(fields[2].charAt(i) - '0',
            (long) Math.pow(10, i)));
      }
      // multiply base offset
      exp = (long) Math.pow(10, fields[1].length() + 1);
      repeatingDecimals.multiple(new SimpleFraction(1, exp));
      // multiply geometric series sum
      exp = (long) Math.pow(10, fields[2].length());
      repeatingDecimals.multiple(new SimpleFraction(exp, exp - 1));
    }

    return  new SimpleFraction(0, 1).add(integerPart).add(nonRepeatingDecimals)
        .add(repeatingDecimals).toString();
  }

  public static void main(String[] args) {
    Fraction fraction = new Fraction("3.14159265.358");
    System.out.println(fraction.toSimpleFraction());
  }

  private static class SimpleFraction {
    long numerator;
    long denominator;

    SimpleFraction(long numerator, long denominator) {
      this.numerator = numerator;
      this.denominator = denominator;
    }

    SimpleFraction add(SimpleFraction other) {
      numerator = numerator * other.denominator + other.numerator * denominator;
      denominator = denominator * other.denominator;
      return simplify();
    }

    SimpleFraction multiple(SimpleFraction other) {
      numerator *= other.numerator;
      denominator *= other.denominator;
      return simplify();
    }

    private SimpleFraction simplify() {
      long gcd = MathUtil.greatestCommonDivisor(numerator, denominator);
      System.out.println(numerator + " " + denominator + " " + gcd);
      numerator /= gcd;
      denominator /= gcd;
      return this;
    }

    @Override
    public String toString() {
      return numerator + "/" + denominator;
    }
  }
}
