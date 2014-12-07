package com.shuoma.ds.math;

import com.shuoma.helper.MathUtil;

public class Fraction {
  long numerator;
  long denominator;

  Fraction(long numerator, long denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  Fraction add(Fraction other) {
    numerator = numerator * other.denominator + other.numerator * denominator;
    denominator = denominator * other.denominator;
    return proper();
  }

  Fraction multiple(Fraction other) {
    numerator *= other.numerator;
    denominator *= other.denominator;
    return proper();
  }

  private Fraction proper() {
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
