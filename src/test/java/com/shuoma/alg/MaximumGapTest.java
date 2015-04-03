package com.shuoma.alg;

import com.shuoma.alg.MaximumGap;
import junit.framework.TestCase;
import org.junit.Test;

public class MaximumGapTest extends TestCase {

  @Test
  public void testMaximumGap() throws Exception {
    MaximumGap ins = new MaximumGap();
    assertEquals(97, ins.maximumGap(new int[] {3, 2, 1, 100}));
  }
}
