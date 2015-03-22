package com.shuoma;

import junit.framework.TestCase;
import org.junit.Test;

public class LongestValidParenthesisTest extends TestCase {

  @Test
  public void testLongestValidParenthese() throws Exception {
    LongestValidParenthesis ins = new LongestValidParenthesis();
    String s = ")()())";
    assertEquals(ins.longestValidParentheses1(s), 4);
    assertEquals(ins.longestValidParentheses(s), "()()");
  }
}
