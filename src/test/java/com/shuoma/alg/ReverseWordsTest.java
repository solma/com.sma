package com.shuoma.alg;

import com.shuoma.alg.ReverseWords;
import junit.framework.TestCase;
import org.junit.Test;

public class ReverseWordsTest extends TestCase {

  @Test
  public void testReverseWords() throws Exception {
    ReverseWords ins = new ReverseWords();
    assertEquals("word reverse 131", ins.reverseWords("131 reverse word"));
  }
}
