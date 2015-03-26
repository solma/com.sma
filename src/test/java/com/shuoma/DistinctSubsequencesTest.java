package com.shuoma;

import junit.framework.TestCase;
import org.junit.Test;

public class DistinctSubsequencesTest extends TestCase {

  @Test
  public void testNumDistinct() throws Exception {
    assertEquals(6, new DistinctSubsequences().numDistinct("rababitit", "rbit"));
  }
}
