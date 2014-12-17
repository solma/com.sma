package com.shuoma.ds.math;


/**
 * Abstraction of a decimal number that can be arbitrary precise.
 */
public class Decimal {

  public static final char DELIMETER_CHARACTER = '.';
  public static final char ESCAPE_CHARACTER = '\\';

  /** String representation of decimals. Repeating decimals are preceded by a dot. */
  String value;

  public Decimal(String value) {
    this.value = value;
  }

  /** Return the simple form of the fraction. */
  public String toSimpleFraction() {
    String[] fields = value.split(ESCAPE_CHARACTER + String.valueOf(DELIMETER_CHARACTER));

    Fraction integerPart =
        new Fraction(Long.parseLong(fields[0]), 1);

    Fraction nonRepeatingDecimals =
        new Fraction(fields[1].equals("") ? 0 : Long.parseLong(fields[1]),
            (long) Math.pow(10, fields[1].length()));

    Fraction repeatingDecimals = new Fraction(0, 1);
    if (fields.length ==3 && fields[2].length() > 0) {
      long exp;
      for (int i = 0; i < fields[2].length(); i++) {
        repeatingDecimals.add(new Fraction(fields[2].charAt(i) - '0',
            (long) Math.pow(10, i)));
      }
      // multiply base offset
      exp = (long) Math.pow(10, fields[1].length() + 1);
      repeatingDecimals.multiple(new Fraction(1, exp));
      // multiply geometric series sum
      exp = (long) Math.pow(10, fields[2].length());
      repeatingDecimals.multiple(new Fraction(exp, exp - 1));
    }

    return  new Fraction(0, 1).add(integerPart).add(nonRepeatingDecimals)
        .add(repeatingDecimals).toString();
  }

  @Override
  public String toString() {
    return value;
  }

  public static void main(String[] args) {
    Decimal fraction = new Decimal("0.05");
    System.out.println(fraction.toSimpleFraction());
  }
}
