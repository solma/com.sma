package com.shuoma.util;

import static com.shuoma.util.ArrayUtil.partition;
import junit.framework.TestCase;

import java.util.Arrays;

public class ArrayUtilTest extends TestCase {

  public void testPartition() throws Exception {
    for (int i = 0; i < 20000; i++) {
      int len = 100;
      int[] a = RandomUtil.genRandomArray(len, len, true, true);
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);

      int pivotIdx = RandomUtil.r.nextInt(len);
      int pivot = a[pivotIdx];
      int idx = partition(Arrays.copyOf(a, a.length), 0, a.length - 1, pivotIdx);

      assertEquals(pivot, b[idx]);
    }
  }
}
