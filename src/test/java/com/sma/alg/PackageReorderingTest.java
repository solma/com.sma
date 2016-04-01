package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class PackageReorderingTest extends TestCase {

  @Test public void testReorder() throws Exception {
    PackageReordering ins = new PackageReordering();
    int[] arr = {1, 2, 5, 8, 10, 4, 3, 6, 9, 7};
    List<List<Integer>> ordered = ins.reorder(arr);
    assertEquals(3, (int) ordered.get(2).get(0));
  }
}
