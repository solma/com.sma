package com.shuoma.alg;

import com.shuoma.alg.DecodeWays;
import junit.framework.TestCase;
import org.junit.Test;

public class DecodeWaysTest extends TestCase {

  @Test
  public void testNumDecodings() throws Exception {
    assertEquals(1, new DecodeWays().numDecodings("101"));
  }
}
