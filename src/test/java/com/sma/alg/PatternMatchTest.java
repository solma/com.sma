package com.sma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PatternMatchTest {

  @Test public void testMatch() throws Exception {
    PatternMatch ins = new PatternMatch();
    assertEquals(true, ins.match("abba", "redbluebluered"));
    assertEquals(true, ins.match("aaaa", "redredredred"));
    assertEquals(false, ins.match("abab", "redbluebluered"));
    assertEquals(false, ins.match("abba", "redredredred"));
  }
}
