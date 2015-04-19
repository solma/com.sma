package com.shuoma.alg;

/** find all positive integers smaller than n that contains digit K (0~9). */

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

@Tag(algs = Arithmetic)
public class NumbersContainCertainDigit {

  public static void main(String[] args) {
    new NumbersContainCertainDigit().find(212, '7');
  }

  // only count numbers
  int count(int n, char k) {
    char[] nry = String.valueOf(n).toCharArray();
    int nl = nry.length;
    if (nl == 0)
      return 0;
    int cnt;
    if (nl > 1 && nry[nl - 2] == k)
      cnt = nry[nl - 1] - 1;
    else
      cnt = nry[nl - 1] > k ? 1 : 0;

    int base = 1, baseCnt = 1;
    for (int i = nl - 2; i >= 0; i--) {
      cnt += (nry[i] - '0') * baseCnt;
      base *= 10;
      baseCnt = baseCnt * 9 + base;
    }
    return cnt;
  }

  int count2s(int N) {
    int countOf2s = 0, digit = 0;
    int j = N, seenDigits = 0, position = 0, pow10_pos = 1;

    /* maintaining this value instead of calling pow() is an 6x perf
     * gain (48s -> 8s) pow10_posMinus1. maintaining this value
     * instead of calling Numof2s is an 2x perf gain (8s -> 4s).
     * overall > 10x speedup */
    while (j > 0) {
      digit = j % 10;
      int pow10_posMinus1 = pow10_pos / 10;
      countOf2s +=
          digit * position * pow10_posMinus1; //10->1, 100->20, 1000->300..., 10^n->n*(10^n-1)

      /* we do this if digit <, >, or = 2
       * Digit < 2 implies there are no 2s contributed by this digit.
       * Digit == 2 implies there are 2 * (numof2s contributed by the previous position) + (numof2s contributed by the presence of this 2) */
      if (digit == 2) {
        countOf2s += seenDigits + 1;
      }

      /* Digit > 2 implies there are digit * (num of 2s by the prev. position) + 10^position */
      else if (digit > 2) {
        countOf2s += pow10_pos;
      }
      seenDigits += pow10_pos * digit;
      pow10_pos *= 10;
      position++;
      j /= 10;
    }
    return (countOf2s);
  }

  // list all satisfying numbers
  List<Integer> find(int n, char k) {
    char[] ary = String.valueOf(n).toCharArray();
    int len = ary.length;

    List<Integer> numbers = new LinkedList<>();
    if (len == 0)
      return numbers;

    int digit = k - '0';

    List<Integer> tailNumbers = new LinkedList<>();
    tailNumbers.add(digit);
    int base = 1;
    for (int i = len - 2; i >= 0; i--) {
      base *= 10;

      // update numbers
      for (int j = 0; j < ary[i] - '0'; j++) {
        int head = n - n % (base * 10) + j * base;
        for (int tail : tailNumbers) {
          numbers.add(head + tail);
        }
      }

      // update tailNumbers
      List<Integer> copy = new LinkedList<>();
      for (int j = 0; j < 10; j++) {
        int head = j * base;
        if (j != digit) {
          for (int tail : tailNumbers) {
            copy.add(head + tail);
          }
          continue;
        }

        for (int t = 0; t < base; t++) {
          copy.add(head + t);
        }
      }
      tailNumbers = copy;
      //System.out.println(tailNumbers);
    }

    // left over
    if (len > 1 && ary[len - 2] == k) {
      for (int i = 0; i < digit; i++) {
        numbers.add(n - n % 10 + i);
      }
    } else {
      if (n % 10 > digit) {
        numbers.add(n - n % 10 + digit);
      }
    }

    return numbers;
  }
}
