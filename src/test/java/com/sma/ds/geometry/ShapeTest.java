package com.sma.ds.geometry;

import static com.sma.ds.geometry.Shape.areaOfPolygon;
import static com.sma.ds.geometry.Shape.pointInPolygon;
import static com.sma.ds.geometry.Shape.rectanglesOverlap;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShapeTest {

  @Test public void testAreaOfPolygon() throws Exception {
    double delta = Math.pow(10, -5);
    double[][] clockwiseSquare = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};
    double[][] counterClockwiseSquare = {{-1, -1}, {1, -1}, {1, 1}, {-1, 1}};
    double[][] clockwiseTriangle = {{-1, 1}, {1, 1}, {0, 0}};
    assertEquals(4, areaOfPolygon(clockwiseSquare), delta);
    assertEquals(4, areaOfPolygon(counterClockwiseSquare), delta);
    assertEquals(1, areaOfPolygon(clockwiseTriangle), delta);
  }

  @Test public void testRectanglesOverlap() throws Exception {
    double[][] rec1 = new double[][] {{5, 0}, {0, 5}};
    double[][] rec2 = new double[][] {{8, 2}, {3, 3}};
    assertEquals(true, rectanglesOverlap(rec1, rec2));
    double[][] rec3 = new double[][] {{8, -2}, {6, -1}};
    assertEquals(false, rectanglesOverlap(rec1, rec3));
  }

  @Test public void testPointInPolygon() throws Exception {
    double[][] clockwisePolygon = new double[][] {{0, 0}, {0, 10}, {10,10}, {6, 6}, {10, 0}};
    double[][] counterclockwisePolygon = new double[][] {{10, 0}, {6, 6}, {10,10}, {0, 10}, {0, 0}};
    assertEquals(true, pointInPolygon(clockwisePolygon, new double[]{5, 10}));
    assertEquals(false, pointInPolygon(clockwisePolygon, new double[]{10, 15}));
    assertEquals(true, pointInPolygon(clockwisePolygon, new double[]{5, 5}));
    assertEquals(true, pointInPolygon(counterclockwisePolygon, new double[]{5, 5}));
  }
}
