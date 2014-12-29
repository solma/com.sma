package com.shuoma.alg.number;

import com.shuoma.util.RandomUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeSieve {
  // Given n, return the primes from 1 to n.
  public static ArrayList<Integer> generateAllPrimes(int n) {
    // is_prime[i] represents (2i + 3) is prime or not.
    ArrayList<Integer> primes = new ArrayList<Integer>(); // stores the primes from 1 to n.
    primes.add(2);
    int size = (int) Math.sqrt(n) + 1;
    boolean isPrime[] = new boolean[size];
    Arrays.fill(isPrime, true);
    for (long i = 0; i < size; ++i) {
      if (isPrime[(int) i]) {
        int p = (int) ((i << 1) + 3);
        primes.add(p);
        // Sieving from p^2, whose index is 2i^2 + 6i + 3.
        for (long j = ((i * i) << 1) + 6 * i + 3; j < size; j += p) {
          isPrime[(int) j] = false;
        }
      }
    }
    return primes;
  }

  public static ArrayList<Integer> generateAllPrimesBase(int n) {
    boolean isPrime[] = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    for (int i = 2; i <= n; i++) {
      for (int j = 2; j <= Math.sqrt(i); j++) {
        //System.out.println(i + " " + j + " " + isPrime[i] + " " + isPrime[j]);
        if (isPrime[i] && isPrime[j] && i % j == 0) {
          isPrime[i] = false;
          break;
        }
      }
    }
    ArrayList<Integer> primes = new ArrayList<Integer>();
    for(int i = 2; i < isPrime.length; i++) {
      if (isPrime[i])
        primes.add(i);
    }
    return primes;
  }

  public static void validate(int n) {
    ArrayList<Integer> primes = generateAllPrimes(n);
    for (Integer prime : primes) {
      for (int j = 2; j < prime; ++j) {
        assert (prime % j != 0);
      }
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      int n = RandomUtil.r.nextInt(100) + 1;
      //validate(n);
      System.out.println("n = " + n + " , " + generateAllPrimesBase(n));
    }
  }
}
