package com.shuoma.alg.number;

/** find all positive integers smaller than n that contains digit K (0~9). */

import java.util.LinkedList;
import java.util.List;

public class NumbersContainCertainDigit {

  public static void main(String[] args) {
    new NumbersContainCertainDigit().find(212, '7');
  }

  // only count numbers
  int count(int n, char k) {
    char[] nry = String.valueOf(n).toCharArray();
    int nl = nry.length;
    if (nl == 0) return 0;
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

  List<Integer> find(int n, char k) {
    char[] ary = String.valueOf(n).toCharArray();
    int len = ary.length;

    List<Integer> numbers = new LinkedList<>();
    if (len == 0) return numbers;

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
