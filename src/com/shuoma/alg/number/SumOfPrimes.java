package com.shuoma.alg.number;

import java.util.ArrayList;

public class SumOfPrimes {
  public static void main(String[] args) {
    int N = 1000;
    ArrayList<Integer> primes = new ArrayList<Integer>();
    int i = 2;
    boolean isPrime;
    int sum = 0;
    while (true) {
      if (primes.size() == N)
        break;
      isPrime = true;
      for (Integer p : primes) {
        if (i % p == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        sum += i;
        primes.add(i);
      }
      i++;
    }
    System.out.println(sum);
  }
}
