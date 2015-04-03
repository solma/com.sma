package com.shuoma.alg;

import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;
import org.junit.Test;

public class AnagramTest extends TestCase {

  @Test
  public void testAnagrams() throws Exception {
    Anagram ins = new Anagram();
    assertArrayEquals(new String[]{"icde", "cdei", "ecid"},
        ins.anagrams(new String[] {"icde", "cdei", "adae", "aeea", "ecid"}).toArray(new String[0]));
  }
}
