package com.shuoma.ds.geometry;

public class Circle {

  public static void main(String[] args) throws Exception {
    Circle c = new Circle(new Point(0, 0), 1);
    System.out.println(c.isIntersect(new Segment(new Point(2, 2), new Point(.19, -2))));
  }

  Point c;
  double r;

  public Circle(Point center, double radius) {
    this.c = center;
    this.r = radius;
  }

  /**
   * Check if a circle is intersected with a segment.
   */
  public boolean isIntersect(Segment seg) {
    double d1 = Point.distance(c, seg.s), d2 = Point.distance(c, seg.e);
    double d = seg.getDistance(c);
    System.out.println(d1 + " " + d2 + " " + d);
    if ((d1 >= r || d2 >= r) && d <= r) return true;
    else return false;
  }
}
