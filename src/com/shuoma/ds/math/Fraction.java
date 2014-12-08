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
    //System.out.println(numerator + " " + denominator + " " + gcd);
    numerator /= gcd;
    denominator /= gcd;
    return this;
  }

  private Decimal toDecimal() {
    proper();
    StringBuilder sb = new StringBuilder();

    long num = numerator;
    for (int i = 0; i < 20; i++) {
      sb.append(num / denominator);
      if (i == 0) sb.append('.');
      num = (num % denominator) * 10;
    }
    return new Decimal(sb.toString());
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }

  public static void main(String[] args) {
    System.out.println(new Fraction(10, 60).toDecimal());
  }
}
