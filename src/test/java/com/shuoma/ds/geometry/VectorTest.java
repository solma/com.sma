package com.shuoma.ds.geometry;

import static com.shuoma.ds.geometry.Vector.Orientation.CLOCKWISE;
import static com.shuoma.ds.geometry.Vector.Orientation.COUNTERCLOCKWISE;
import static com.shuoma.ds.geometry.Vector.crossOrientation;
import static com.shuoma.ds.geometry.Vector.linePointDist;
import static com.shuoma.ds.geometry.Vector.linesIntersect;
import static com.shuoma.ds.geometry.Vector.segmentsIntersect;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

public class VectorTest extends TestCase {

  private static double EQUAL_DELTA = 10e-3;

  @Test
  public void testLinePointDistance() throws Exception {
    assertEquals(1.414, linePointDist(new double[][]{{1, 1}, {1, -1}},
        new double[] {2, 2}, true), EQUAL_DELTA);
  }

  @Test
  public void testOrientation() throws Exception {
    assertEquals(COUNTERCLOCKWISE, crossOrientation(new double[] {1, 1}, new double[] {1, -1},
        new double[] {0, 0}));
    assertEquals(CLOCKWISE, crossOrientation(new double[] {1, 1}, new double[] {1, -1},
            new double[] {2, 0}));
  }

  @Test
  public void testLineIntersect() throws Exception {
    double[] l1 = new double[]{1, 1, 0};
    double[] l2 = new double[]{0, 1, 2};
    double[] l3 = new double[]{1, 1, 1};
    assertEquals(true, Arrays.equals(new double[]{-2., 2.}, linesIntersect(l1, l2)));
    assertEquals(null, linesIntersect(l1, l3));
  }

  @Test
  public void testSegmentIntersect() throws Exception {
    double[][] seg1 = new double[][]{{0, 0}, {2, 0}};
    double[][] seg2 = new double[][]{{1, 1}, {1, -1}};
    double[][] seg3 = new double[][]{{3, 0}, {7, 0}};
    assertEquals(true, segmentsIntersect(seg1, seg2));
    assertEquals(false, segmentsIntersect(seg1, seg3));
  }
}
