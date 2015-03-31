package com.shuoma;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class MedianOfTwoSortedArraysTest extends TestCase {

  @Test
  public void testFindMedianSortedArrays() throws Exception {
    MedianOfTwoSortedArrays ins = new MedianOfTwoSortedArrays();
    int A[] = new int[] {1, 2, 4, 8, 9, 10};
    int B[] = new int[] {3, 5, 6, 7};
    Arrays.sort(A);
    Arrays.sort(B);
    assertEquals(5.5, ins.findMedianSortedArrays(A, B));
  }
}
