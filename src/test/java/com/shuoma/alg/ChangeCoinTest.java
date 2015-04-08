package com.shuoma.alg;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Set;

public class ChangeCoinTest extends TestCase {

  @Test
  public void test() {
    ChangeCoin ins = new ChangeCoin();
    int n = 20;
    int[] denominations = new int[] {1, 2, 5, 10};
    Set[] res = new Set[2];
    res[0] = ins.changeRecursion(n, denominations);
    res[1] = ins.changeCntDp(n, denominations);
    assertEquals(res[0], res[1]);
  }
}
