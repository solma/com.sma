package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Arithmetic;
import static com.sma.annotation.Tag.Algorithm.Greedy;
import static com.sma.annotation.Tag.DataStructure.Array;

import com.sma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(algs = {Arithmetic, Greedy}, dss = Array) public class PrimeNumber {

  // Given n, return the primes from 1 to n.
  List<Integer> allPrimesSmallerThan(int n) {

    // we do not need to sieve even numbers
    // so isPrime[i] represents (2i + 3) is prime or not.
    List<Integer> primes = new ArrayList<>(); // stores the primes from 1 to n.
    if (n < 2) { return primes; }
    primes.add(2);
    int indexMax = (n - 3) >> 1;
    boolean isPrime[] = new boolean[indexMax + 1];
    Arrays.fill(isPrime, true);
    for (int i = 0; i <= indexMax; i++) {
      if (!isPrime[i]) { continue; }
      long p = (i << 1) + 3;
      primes.add((int) p);
      // Sieving from p^2, whose index is 2i^2 + 6i + 3
      // index increase by p since even numbers are skipped
      //for (int j = ((i * i) << 1) + 6 * i + 3; j <= indexMax; j += p) {
      for (long j = p * p; j <= n; j += 2 * p) {
        isPrime[(int) (j - 3) >> 1] = false;
      }
    }
    return primes;
  }
}
