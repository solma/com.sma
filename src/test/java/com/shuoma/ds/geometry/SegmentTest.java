package com.shuoma.ds.geometry;

import static com.shuoma.ds.geometry.Segment.Orientation.CLOCKWISE;
import static com.shuoma.ds.geometry.Segment.Orientation.COUNTERCLOCKWISE;

import junit.framework.TestCase;
import org.junit.Test;

public class SegmentTest extends TestCase {

  private static double EQUAL_DELTA = 10e-3;

  @Test
  public void testGetDistance() throws Exception {
    Segment seg = new Segment(new Point(1, 1), new Point(1, -1));
    assertEquals(1.414, seg.getDistance(new Point(2, 2)), EQUAL_DELTA);
  }

  @Test
  public void testGetOrientation() throws Exception {
    Segment seg = new Segment(new Point(1, 1), new Point(1, -1));
    assertEquals(CLOCKWISE, seg.getOrientation(new Point(0, 0)));
    assertEquals(COUNTERCLOCKWISE, seg.getOrientation(new Point(2, 0)));
    assertEquals(COUNTERCLOCKWISE, seg.getOrientation(new Point(2, 0)));
  }

  @Test
  public void testIsIntersect() throws Exception {
    Segment seg1 = new Segment(new Point(0, 0), new Point(2, 0));
    Segment seg2 = new Segment(new Point(1, 1), new Point(1, -1));
    Segment seg3 = new Segment(new Point(3, 0), new Point(7, 0));
    assertEquals(true, seg1.isIntersect(seg2));
    assertEquals(false, seg1.isIntersect(seg3));
  }
}
