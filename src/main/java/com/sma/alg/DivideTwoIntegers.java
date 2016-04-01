package com.sma.alg;
//pay attention to special case of divisorLong: negatives, 0, 1

import static com.sma.annotation.Tag.Algorithm.BitManipulation;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.*;
import java.lang.Math;

@Tag(algs = BitManipulation, references = LeetCode)
public class DivideTwoIntegers {
  public static void main(String[] args) {
    int dividendLong = Integer.parseInt(args[0]), divisorLong = Integer.parseInt(args[1]);
    new DivideTwoIntegers().main(dividendLong, divisorLong);
  }

  public void main(int dividendLong, int divisorLong) {
    //System.out.println(divide_ref(dividendLong, divisorLong)+" "+dividendLong/divisorLong);
    System.out.println(divide(dividendLong, divisorLong) + " " + dividendLong / divisorLong);
  }

  public int divide(int dividend, int divisor) {//tricky, need to upgrade from int to long
    if (dividend == 0)
      return 0;

    boolean sign;
    if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
      sign = false;
    else
      sign = true;

    long dividendLong = Math.abs((long) dividend);
    long divisorLong = Math.abs((long) divisor);
    if (dividendLong < divisorLong)
      return 0;
    int quo;
    if (Math.abs(divisorLong) > 1) {

      ArrayList<Long> exponents = new ArrayList<>();
      long base = divisorLong;
      while (true) {
        exponents.add(base);
        base <<= 1;
        //System.out.println(base+"  "+(base<<1) );
        if (base > dividendLong)
          break;
      }
      int i = exponents.size() - 1;
      long remainder = dividendLong;
      quo = 0;
      while (true) {
        base = exponents.get(i);
        if (remainder >= base) {
          quo += (int) Math.pow(2, i);
          System.out.println(base + " " + quo);
          remainder -= base;
        }
        i -= 1;
        if (i < 0)
          break;
      }
    } else {
      quo = (int) dividendLong;
    }
    if (!sign)
      quo = -quo;
    return quo;
  }
}
