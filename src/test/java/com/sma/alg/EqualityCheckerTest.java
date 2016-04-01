package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class EqualityCheckerTest extends TestCase {

  @Test
  public void testIsValid() throws Exception {
    EqualityChecker ins = new EqualityChecker();
    String[] equality = {"A = B", "B = C", "D = E", "C = D"};
    String[] inequality = {"X ~ E", "A ~ E"};
    assertEquals(false, ins.isValid(equality, inequality));
  }
}
