package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

@Tag(algs = Arithmetic, references = {LeetCode})
public class NumbersContainCertainDigit {

  // count appearance of digit K in numbers <= n
  int countAppearances(int n, char k) {
    if (n <= 0) return 0;
    char[] nry = String.valueOf(n).toCharArray();
    int nl = nry.length;
    int cnt = 0;
    for (int i = 0; i < nl; i++) {
      int idx = nl - 1 - i;
      if (idx > 0) {
        //9->1, 99->20, 999->300, ..., 10^n-1->n*(10^n-1)
        cnt += (nry[i] - '0') * (idx * Math.pow(10, idx - 1));
        if (nry[i] >= k) {
          if (nry[i] == k) {
            cnt += Integer.valueOf(String.valueOf(nry, i + 1, nl - i - 1)) + 1;
          } else {
            cnt += Math.pow(10, idx);
          }
        }
      } else {
        cnt += nry[i] >= k ? 1 : 0;
      }
    }
    return cnt;
  }

  // only count number of numbers that contains digit K
  int countNumbers(int n, char k) {
    if (n <= 0) return 0;
    char[] nry = String.valueOf(n).toCharArray();
    int nl = nry.length;
    int cnt = nry[nl - 1] >= k ? 1 : 0;

    int base = 10, baseCnt = 1;
    for (int i = nl - 2; i >= 0; i--) {
      if (nry[i] >= k) {
        if (nry[i] > k) cnt += base - baseCnt;
        else cnt = Integer.valueOf(String.valueOf(nry, i + 1, nl - i - 1)) + 1;
      }
      cnt += (nry[i] - '0') * baseCnt;
      //System.out.println(nry[i] + " " + cnt);
      baseCnt = baseCnt * 9 + base;
      base *= 10;
    }
    return cnt;
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
