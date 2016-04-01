package com.sma.alg;

import junit.framework.TestCase;
import org.junit.Test;

public class MedianFinderTest extends TestCase {

  @Test
  public void testMedianFinder() {
    MedianFinder mf = new MedianFinder();
    mf.addNum(2);
    mf.addNum(1);
    double delta = .0001;
    assertEquals(1.5, mf.findMedian(), delta);
    mf.addNum(3);
    assertEquals(2, mf.findMedian(), delta);
  }
}
