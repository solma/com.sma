package com.shuoma;
public class FactorialTrailingZeros {
  public static void main(String[] args) {
    System.out.println(trailingZeroes(100));
  }

  public static int trailingZeroes(int n) {
    int cnt = 0;
    while (n > 0) {
      n /= 5;
      cnt += n;
    }
    return cnt;
  }
}
