package com.shuoma.alg;

import com.shuoma.util.RandomUtil;
import org.junit.Test;

import java.util.Arrays;

public class SelectingTest {

  @Test
  public void testTestSelectionAlgorithms() throws Exception {
    Selecting ins = new Selecting();
    int sortAlgorithmChoice;
    for (int code = 1; code <= 1; code++) {
      sortAlgorithmChoice = code;
      for (int i = 0; i < 100; i++) {
        int[] a = RandomUtil.genRandomArrayWithMinSize(10);
        int K;
        for (int j = 1; j <= a.length; j++) {
          K = j;
          int[] cpy = Arrays.copyOf(a, a.length);
          switch (sortAlgorithmChoice) {
            case 1:
              ins.quickSelect(cpy, K);
              break;
            case 2:
              ins.selectKthLargest(cpy, K);
              break;
            default:
              break;
          }
          Arrays.sort(a);
          if (a[K - 1] != cpy[K - 1])
            System.out.println(Arrays.toString(cpy) + " " + K + " " + a[K - 1] + " " + cpy[K - 1]);
        }
      }
    }
  }
}
