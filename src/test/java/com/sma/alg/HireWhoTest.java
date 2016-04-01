package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class HireWhoTest extends TestCase {

  @Test
  public void testHireWho() throws Exception {
    HireWho ins = new HireWho();
    int[] ability = {7, 3, 5, 2, 9, 7};
    int budget = 20;
    int[] cost = {6, 2, 6, 3, 9, 7};
    int nM = 2, nF = 2;
    boolean[] sex = {true, true, true, false, false, false};
    assertEquals(
        ins.hireWhoRecursion(ability, cost, sex, nM, nF, budget),
        ins.hireWhoDp(ability, cost, sex, nM, nF, budget));
  }
}
