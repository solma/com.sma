package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.DataStructure.StringT;

import com.sma.annotation.Tag;

@Tag(algs = Arithmetic, dss = StringT)
public class PrimePalindrome {
  public static void main(String[] args) {
    int N = 1000;
    while (N >= 0) {
      N--;
      if (isPalindrome(N) && isPrime(N)) {
        System.out.println(N);
        break;
      }
    }
  }

  public static boolean isPrime(int num) {
    int sqrt = (int) (Math.sqrt(num) + 1);
    for (int i = 2; i <= sqrt; i++) {
      if (num % i == 0)
        return false;
    }
    return true;
  }

  public static boolean isPalindrome(int num) {
    String s = java.lang.String.valueOf(num);
    int l = 0, r = s.length() - 1;
    while (l < r) {
      if (s.charAt(l) != s.charAt(r))
        return false;
      l++;
      r--;
    }
    return true;
  }
}
