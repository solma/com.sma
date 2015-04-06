package com.shuoma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class KeyStrokePathTest extends TestCase {

  @Test
  public void testGetPath() throws Exception {
    KeyStrokePath ins = new KeyStrokePath();
    int n = 8;
    /**
     * Keyboard Layout
     * A B C D E F G H
     * I J K L M N O P
     * Q R S T U V W X
     * Y Z
     */
    assertEquals("rr", ins.getPath('B', 'D', n));
    assertEquals("ddll", ins.getPath('G', 'U', n));
    assertEquals("lllllddl", ins.getPath('O', 'Y', n));
  }
}
