package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import com.shuoma.util.RandomUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeSieveTest {

  @Test
  public void generateAllPrimesTest() {
    PrimeSieve ins = new PrimeSieve();
    for (int i = 0; i < 10; i++) {
      int n = RandomUtil.r.nextInt(1000) + 1;
      List[] primeLists = new List[2];
      primeLists[0] = generateAllPrimesBase(n);
      primeLists[1] = ins.generateAllPrimes(n);
//      if (!primeLists[0].equals(primeLists[1])) {
//        System.out.println(primeLists[0]);
//        System.out.println(primeLists[1]);
//        System.out.println(n + "\n");
//      }
      assertEquals(primeLists[0], primeLists[1]);
    }
  }

  List<Integer> generateAllPrimesBase(int n) {
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
