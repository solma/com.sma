package com.shuoma.ds.geometry;

import static com.shuoma.ds.geometry.Vector.Orientation;
import static com.shuoma.ds.geometry.Vector.Orientation.CLOCKWISE;
import static com.shuoma.ds.geometry.Vector.Orientation.COLLINEAR;
import static com.shuoma.ds.geometry.Vector.Orientation.COUNTERCLOCKWISE;
import static com.shuoma.ds.geometry.Vector.cross;
import static com.shuoma.ds.geometry.Vector.crossOrientation;
import static com.shuoma.ds.geometry.Vector.distance;
import static com.shuoma.ds.geometry.Vector.linePointDist;
import static com.shuoma.ds.geometry.Vector.pointOnSegment;

/**
 * Any shape more than one line
 */
public class Shape {

  /** <code>polygon</code>'s points needs to be clockwise or counter-clockwise. */
  static double areaOfPolygon(double[][] polygon) {
    return Math.abs(areaAndOrientationOfPolygon(polygon));
  }

  static double areaAndOrientationOfPolygon(double[][] polygon) {
    double area = 0.;
    int N = polygon.length;

    double[] origin = {0., 0.};
//    for (int i = 0; i < N; i++) {
//      area += cross(origin, polygon[(i + 1) % N], polygon[i % N]);
//    }

    for (int i = 1; i <= N - 2; i++) {
      area += cross(polygon[0], polygon[(i + 1) % N], polygon[i % N]);
    }
    return area / 2.0;
  }

  /** Return true if circle intersects with segment. */
  static boolean circleSegmentIntersect(double[] circleCenter, double circleRadius, double[][] seg) {
    double d1 = distance(circleCenter, seg[0]), d2 = distance(circleCenter, seg[1]);
    double d = linePointDist(seg, circleCenter, true);
    System.out.println(d1 + " " + d2 + " " + d);
    return (d1 >= circleRadius || d2 >= circleRadius) && d <= circleRadius;
  }

  /** Return true if two rectangles overlap
   * 1st element is bottom right, 2nd element is top left */
  static boolean rectanglesOverlap(double[][] rec1, double[][] rec2) {

    return ! (rec1[1][1] < rec2[0][1] || rec2[1][1] < rec1[0][1]
        || rec1[0][0] < rec2[1][0] || rec2[0][0] < rec1[1][0]);
// or
//    return rec1[0][0] >= rec2[1][0] && rec1[0][1] <= rec2[1][1]
//        && rec1[1][0] <= rec2[0][0] && rec1[1][1] >= rec2[0][1];
  }

  /** Return the orientation of points of polygon. */
  static Orientation orientationOfPolygon(double[][] polygon) {
    double area = areaAndOrientationOfPolygon(polygon);
    assert (area != 0);
    //System.out.println("area: " + area);
    return area > 0 ? CLOCKWISE : COUNTERCLOCKWISE;
  }

  /***
   * @return true if point is in (or on) the polygon
   */
  static boolean pointInPolygon(double[][] polygon, double[] X) {
    assert (polygon.length >= 3);
    Orientation polygonOrientation = orientationOfPolygon(polygon);
    //System.out.println("polygon is: " + polygonOrientation);

    int len = polygon.length;
    for(int i = 0; i < len; i++){
      double[] s = polygon[i % len], e = polygon[(i + 1) % len];
      Orientation o = crossOrientation(s, e, X);
      if (polygonOrientation == o) { return false; }
      if (o == COLLINEAR && pointOnSegment(new double[][] {s, e}, X)) { return true; }
    }
    return true;
  }
}
