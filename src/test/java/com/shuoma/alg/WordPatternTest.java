package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordPatternTest {

  @Test public void testMatch() throws Exception {
    WordPattern ins = new WordPattern();
    assertEquals(true, ins.match("abba", "redbluebluered"));
    assertEquals(true, ins.match("aaaa", "redredredred"));
    assertEquals(false, ins.match("abab", "redbluebluered"));
    assertEquals(false, ins.match("abba", "redredredred"));
  }
}
