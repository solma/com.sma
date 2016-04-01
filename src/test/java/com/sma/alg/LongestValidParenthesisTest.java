package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class LongestValidParenthesisTest extends TestCase {

  @Test
  public void testLongestValidParenthese() throws Exception {
    LongestValidParenthesis ins = new LongestValidParenthesis();
    String s = ")()())";
    assertEquals(ins.longestValidParentheses(s), 4);
  }
}
