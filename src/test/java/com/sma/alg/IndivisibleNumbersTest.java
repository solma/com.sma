package com.sma.alg;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IndivisibleNumbersTest {

  @Test public void testCount() throws Exception {
    IndivisibleNumbers ins = new IndivisibleNumbers();
    List<Integer> filterNumbers = Arrays.asList(2, 3, 5, 7, 9);
    int N = 100;
    long[] res = new long[2];
    res[0] = dummyCount(filterNumbers, N);
    res[1] = ins.count(filterNumbers, N);
    //System.out.println(Arrays.toString(res));
  }

  int dummyCount(List<Integer> filterNumbers, long N) {
    List<Integer> indivisible = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      boolean flag = true;
      for (int x : filterNumbers) {
        if (i % x == 0) {
          flag = false;
          break;
        }
      }
      if (flag) indivisible.add(i);
    }
    //System.out.println(indivisible);
    return indivisible.size();
  }
}
