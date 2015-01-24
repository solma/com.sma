package com.shuoma.alg.number;

// count number of positive integers smaller than n that contains digit K (0~9)

public class NumbersContainCertainDigit {
  public static void main(String[] args) {
    int n = 2128;
    char k = '7';
    System.out.println(count(n, k) + " " + countBase(n, k));
  }

  public static int count(int n, char k) {
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

  public static int countBase(int n, char k) {
    int cnt = 0;
    for (int i = 1; i < n; i++) {
      if (String.valueOf(i).contains(k + "")) cnt++;
    }
    return cnt;
  }
}
