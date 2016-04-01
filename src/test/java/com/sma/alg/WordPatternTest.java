package com.sma.alg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordPatternTest {

  @Test public void testMatch() throws Exception {
    WordPattern ins = new WordPattern();
    assertEquals(true, ins.wordPattern("x", "redbluebluered"));
    assertEquals(true, ins.wordPattern("abba", "red blue blue red"));
    assertEquals(true, ins.wordPattern("abba", "red blue blue red"));
    assertEquals(true, ins.wordPattern("aaaa", "red red red red"));
    assertEquals(false, ins.wordPattern("abab", "red blue blue red"));
    assertEquals(false, ins.wordPattern("abba", "red red red red"));
  }
}
