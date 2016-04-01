package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class AnagramTest extends TestCase {

  @Test
  public void testAnagrams() throws Exception {
    Anagram ins = new Anagram();
    assertArrayEquals(new String[]{"icde", "cdei", "ecid"},
        ins.anagrams(new String[] {"icde", "cdei", "adae", "aeea", "ecid"}).toArray(new String[0]));
  }
}
