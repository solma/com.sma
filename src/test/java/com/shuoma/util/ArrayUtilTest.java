package com.shuoma.util;

import static com.shuoma.util.ArrayUtil.partition;
import junit.framework.TestCase;

import java.util.Arrays;

public class ArrayUtilTest extends TestCase {

  public void testPartition() throws Exception {
    for (int i = 0; i < 100; i++) {
      int len = 20;
      int[] a = RandomUtil.genRandomArray(len, len, true, true);
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);

      int pivotIdx = RandomUtil.r.nextInt(len);
      int pivot = a[pivotIdx];
      int idx = partition(Arrays.copyOf(a, a.length), 0, a.length - 1, pivotIdx);

      assertEquals(pivot, b[idx]);
    }

    int[] num = new int[] {4, 3, 2, 5};
    ArrayUtil.partition(num, 0, 3, 1);
//    System.out.println(Arrays.toString(num));
  }
}
