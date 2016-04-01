package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class WildcardMatchingTest extends TestCase {

  @Test
  public void testIsMatch() throws Exception {
    WildcardMatching ins = new WildcardMatching();
    assertEquals(true,
        ins.isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaab", "a*******b"));
  }
}
