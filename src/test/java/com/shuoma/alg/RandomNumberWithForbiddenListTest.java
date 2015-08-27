package com.shuoma.alg;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class RandomNumberWithForbiddenListTest extends TestCase {

  @Test
  public void testRandomWithForbidden() throws Exception {
    RandomNumberWithForbiddenList ins = new RandomNumberWithForbiddenList();
    int rep = 200000;
    int N = 10;
    int[] cnt = new int[N];
    int[] forbidden = {4, 5, 9};
    for (int i = 0; i < rep; i++) {
      cnt[ins.randomWithForbidden(N, forbidden)]++;
    }
    System.out.println(Arrays.toString(cnt));
  }
}
