package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

/**
 Write a program to check whether a given number is an ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 Note that 1 is typically treated as an ugly number.
 */
@Tag(algs = Arithmetic, dss = StringT, references = LeetCode)
public class UglyNumber {

  boolean isUgly(int num) {
    if (num == 0) return false;

    int[] factors = {2, 3, 5};
    for (int factor : factors) {
      while (num % factor == 0) { num /= factor; }
    }
    return num == 1;
  }
}
