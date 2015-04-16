package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;
import static com.shuoma.annotation.Tag.DataStructure.Array;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(algs = Arithmetic, dss = Array)
public class PrimeSieve {
  // Given n, return the primes from 1 to n.
  public List<Integer> generateAllPrimes(int n) {

    // we do not need to sieve even numbers
    // so isPrime[i] represents (2i + 3) is prime or not.
    List<Integer> primes = new ArrayList<>(); // stores the primes from 1 to n.
    if (n < 2) {
      return primes;
    }
    primes.add(2);
    int indexMax = (n - 3) >> 1;
    boolean isPrime[] = new boolean[indexMax + 1];
    Arrays.fill(isPrime, true);
    for (int i = 0; i <= indexMax; i++) {
      if (isPrime[i]) {
        int p = (i << 1) + 3;
        primes.add(p);
        // Sieving from p^2, whose index is 2i^2 + 6i + 3
        // index increase by p since even numbers are skipped
        //for (int j = ((i * i) << 1) + 6 * i + 3; j <= indexMax; j += p) {
        for (int j = p * p; j <= n; j += 2 * p) {
          isPrime[(j - 3) >> 1] = false;
        }
      }
    }
    return primes;
  }
}
