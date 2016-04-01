package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class ScrambleStringTest extends TestCase {

  @Test
  public void testIsScramble() throws Exception {
    assertEquals(true, new ScrambleString().isScramble("great", "rgate"));
  }
}
