package com.shuoma.alg;

import com.shuoma.alg.LongestSubstringWithAtMostTwoDistinctCharacters;
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
