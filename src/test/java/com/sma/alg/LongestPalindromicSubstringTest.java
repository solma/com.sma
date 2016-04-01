package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class LongestPalindromicSubstringTest extends TestCase {

  @Test
  public void testLongestPalindrome() throws Exception {
    LongestPalindromicSubstring ins = new LongestPalindromicSubstring();
    assertEquals("caac", ins.longestPalindrome("acaacb"));
  }
}
