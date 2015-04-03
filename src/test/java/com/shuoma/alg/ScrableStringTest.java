package com.shuoma.alg;

import com.shuoma.alg.ScrableString;
import junit.framework.TestCase;
import org.junit.Test;

public class ScrableStringTest extends TestCase {

  @Test
  public void testIsScramble() throws Exception {
    assertEquals(true, new ScrableString().isScramble("great", "rgate"));
  }
}
