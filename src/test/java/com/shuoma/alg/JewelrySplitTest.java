package com.shuoma.alg;

import org.junit.Test;

public class JewelrySplitTest {

  @Test public void testSplit() throws Exception {
    JewelrySplit ins = new JewelrySplit();
    int[] arr = {1, 2, 3, 4, 5, 5};
    System.out.println(ins.split(arr));
  }
}
