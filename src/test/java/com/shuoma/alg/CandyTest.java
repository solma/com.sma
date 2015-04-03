package com.shuoma.alg;

import com.shuoma.alg.Candy;
import junit.framework.TestCase;
import org.junit.Test;

public class CandyTest extends TestCase {

  @Test
  public void testCandy() throws Exception {
    assertEquals(9, new Candy().candy(new int[] {1, 2, 3, 3, 2}));
  }
}
