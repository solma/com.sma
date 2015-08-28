package com.shuoma.ds.geometry;

/**
 * Vector(i.e. line, segment)
 *
 * @see <a href="https://goo.gl/81wOm3">topcode geometry tutorial</a>
 */
public class Vector {

  public enum Orientation {
    CLOCKWISE,
    COUNTERCLOCKWISE,
    COLLINEAR,
    ;

    static Orientation getOppo(Orientation o) {
      assert (o != COLLINEAR);
      return o == CLOCKWISE ? COUNTERCLOCKWISE : CLOCKWISE;
    }
  }

  /** Compute the dot product AB ⋅ BC */
  static double dot(double[] A, double[] B, double[] C) {
    double[] AB = new double[2];
    double[] BC = new double[2];
    AB[0] = B[0] - A[0];
    AB[1] = B[1] - A[1];
    BC[0] = C[0] - B[0];
    BC[1] = C[1] - B[1];
    double dot = AB[0] * BC[0] + AB[1] * BC[1];
    return dot;
  }

  /** Compute the cross product AB x AC */
  static double cross(double[] A, double[] B, double[] C) {
    double[] AB = new double[2];
    double[] AC = new double[2];
    AB[0] = B[0] - A[0];
    AB[1] = B[1] - A[1];
    AC[0] = C[0] - A[0];
    AC[1] = C[1] - A[1];
    double cross = AB[0] * AC[1] - AB[1] * AC[0];
    return cross;
  }

  /** Compute the orientation from AC to AB */
  static Orientation crossOrientation(double[] A, double[] B, double[] C) {
    double cross = cross(A, B, C);
    if (cross == 0.) return Orientation.COLLINEAR;
    return cross > 0 ? Orientation.CLOCKWISE : Orientation.COUNTERCLOCKWISE;
  }

  /** Compute the distance from A to B */
  static double distance(double[] A, double[] B) {
    double d1 = A[0] - B[0];
    double d2 = A[1] - B[1];
    return Math.sqrt(d1 * d1 + d2 * d2);
  }

  /**
   * Compute the distance from AB to C
   * if isSegment is true, AB is a segment, not a line.
   */
  static double linePointDist(double[][] line, double[] C, boolean isSegment) {
    double[] A = line[0];
    double[] B = line[1];
    double dist = Math.abs(cross(A, B, C) / distance(A, B));
    if (isSegment) {
      double dot1 = dot(A, B, C);
      if (dot1 > 0) { return distance(B, C); }
      double dot2 = dot(B, A, C);
      if (dot2 > 0) { return distance(A, C); }
    }
    return dist;
  }

  /**
   * Return the intersection point if lines are not parallel; otherwise return null.
   * line represented by l[0] * x + l[1] * y = l[2]
   */
  static double[] linesIntersect(double[] l1, double[] l2) {
    double det = l1[0] * l2[1] - l1[1] * l2[0];
    if (det == 0) { return null; }
    double x = (l2[1] * l1[2] - l1[1] * l2[2]) / det;
    double y = (l1[0] * l2[2] - l2[0] * l1[2]) / det;
    System.out.println(x + " " + y);
    return new double[] {x, y};
  }

  /** Return true if point is on segment. */
  static boolean pointOnSegment(double[][] seg, double[] p) {
    if (p[0] <= Math.max(seg[0][0], seg[1][0]) && p[0] >= Math.min(seg[0][0], seg[1][0]) &&
        p[1] <= Math.max(seg[0][1], seg[1][1]) && p[1] >= Math.min(seg[0][1], seg[1][1]))
      return true;
    return false;
  }

  /** Return true if two lines intersect */
  static boolean segmentsIntersect(double[][] seg1, double[][] seg2) {
    Orientation o1 = crossOrientation(seg1[0], seg1[1], seg2[0]),
        o2 = crossOrientation(seg1[0], seg1[1], seg2[1]),
        o3 = crossOrientation(seg2[0], seg2[1], seg1[0]),
        o4 = crossOrientation(seg2[0], seg2[1], seg1[1]);

    //System.out.println(o1 + " " + o2 + " " + o3 + " " + o4);
    if ((!o1.equals(o2) && !o3.equals(o4))
        || (o1 == Orientation.COLLINEAR && o1 == o2 && o1 == o3 && o1 == o4 && (pointOnSegment(seg1, seg2[0]) || pointOnSegment(seg1, seg2[1]))))
      return true;
    return false;
  }

  /** Compute the slope of the line passing A and B */
  static double slope(double[] A, double[] B) {
    return (A[0] == B[0]) ? Double.MAX_VALUE : (A[1] - B[1]) / (A[0] - B[0]);
  }
}
