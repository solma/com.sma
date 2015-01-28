package com.shuoma.alg.streaming;

import com.shuoma.util.RandomUtil;
import junit.framework.TestCase;

public class SlidingWindowStatsTest extends TestCase {

  public void testMax() throws Exception {
    int[] ary = RandomUtil.genRandomArray(100, 10, true, false);
    SlidingWindowStats ins = new SlidingWindowStats(3, ary);

  }
}
