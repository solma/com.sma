package com.shuoma.alg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContainsDuplicateTest {

  @Test
  public void testContainsNearbyAlmostDuplicate() throws Exception {
    ContainsDuplicate ins = new ContainsDuplicate();
    assertEquals(true, ins.containsNearbyAlmostDuplicate(new int[]{-1, -1}, 1, 0));
    assertEquals(false, ins.containsNearbyAlmostDuplicate(new int[]{-1, -1}, 1, -1));
  }
}
