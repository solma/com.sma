package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class LongestSubstringWithAtMostTwoDistinctCharactersTest extends TestCase {

  @Test
  public void testLongestSubstring() throws Exception {
    LongestSubstringWithAtMostTwoDistinctCharacters ins =
        new LongestSubstringWithAtMostTwoDistinctCharacters();
    assertEquals("bcbbbbcccb", ins.longestSubstring("abcbbbbcccbdddadacb"));
  }
}
