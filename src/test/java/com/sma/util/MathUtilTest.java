package com.sma.util;

import static com.sma.util.MathUtil.factorial;
import static com.sma.util.MathUtil.gcd;
import junit.framework.TestCase;
import org.junit.Test;

public class MathUtilTest extends TestCase {

  @Test
  public void testFactorial() throws Exception {
    assertEquals(6, factorial(3));
    assertEquals(24, factorial(4));
    assertEquals(120, factorial(5));
  }

  @Test
  public void testGcd() throws Exception {
    assertEquals(3, gcd(6, 9));
    assertEquals(3, gcd(9, 6));
    assertEquals(1, gcd(9, 7));
  }
}
