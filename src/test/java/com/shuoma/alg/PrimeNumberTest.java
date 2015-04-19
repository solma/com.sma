package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import com.shuoma.util.RandomUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PrimeNumberTest {

  PrimeNumber ins = new PrimeNumber();

  @Test
  public void allPrimesSmallerThanTest() {
    for (int i = 0; i < 10; i++) {
      int n = RandomUtil.r.nextInt(1000) + 1;
      List<Integer>[] primeLists = new List[2];
      primeLists[0] = dummyAllPrimesSmallerThan(n);
      primeLists[1] = ins.allPrimesSmallerThan(n);
//      if (!primeLists[0].equals(primeLists[1])) {
//        System.out.println(primeLists[0]);
//        System.out.println(primeLists[1]);
//        System.out.println(n + "\n");
//      }
      assertEquals(primeLists[0], primeLists[1]);
    }
  }

  @Test
  public void factorizationTest() {
    for (int i = 0; i < 10; i++) {
      int n = RandomUtil.r.nextInt(Integer.MAX_VALUE - 2) + 2;
      assertEquals(n, factorsToNumber(ins.factorization(n)));
    }
  }

  int factorsToNumber(Map<Integer, Integer> factors) {
    int n = 1;
    for (int factor : factors.keySet()) {
      n *= Math.pow(factor, factors.get(factor));
    }
    return n;
  }

  List<Integer> dummyAllPrimesSmallerThan(int n) {
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
    List<Integer> primes = new ArrayList<>();
    for(int i = 2; i < isPrime.length; i++) {
      if (isPrime[i])
        primes.add(i);
    }
    return primes;
  }
}
