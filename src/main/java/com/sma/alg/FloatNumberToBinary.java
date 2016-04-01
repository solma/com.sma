package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Algorithm.BitManipulation;
import static com.sma.annotation.Tag.Reference.ElementsOfProgrammingInterviews;

import com.sma.annotation.Tag;

@Tag(algs = {Arithmetic, BitManipulation}, references = ElementsOfProgrammingInterviews)
public class FloatNumberToBinary {

  public static void main(String[] args) {
    FloatNumberToBinary ins = new FloatNumberToBinary();
    System.out.println(ins.bin(123456.789));
  }

  String bin(double d) {
    String[] fields = String.valueOf(d).split("\\.");
    int integerPart = Integer.parseInt(fields[0]);
    double fractional = d - integerPart;
    return fractional > 0 ? (bin(integerPart) + "." + binFraction(fractional)) : bin(integerPart);
  }

  String bin(int n) {
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      sb.append(n & 1);
      n >>>= 1;
    }
    return sb.reverse().toString();
  }

  String binFraction(double d) {
    assert (d < 1);
    double cache = d;
    long base = 2;
    StringBuilder sb = new StringBuilder();
    while (d > .00000001) {
      while (d < 1. / base) {
        sb.append(0);
        base <<= 1;
      }
      d -= 1. / base;
      sb.append(1);
      base <<= 1;
    }
    System.out.println("loss: " + d + " after binary: " + (cache - d));
    return sb.reverse().toString();
  }
}
