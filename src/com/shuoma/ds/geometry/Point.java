package com.shuoma.ds.geometry;

import java.util.HashSet;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return (int) (x + y);
  }

  /*
   * @Override public boolean equals(Object p){ return (x==((Point)p).x) && (y==((Point)p).y); }
   */

  @Override
  public String toString() {
    return x + "," + y;
  }

  public static void main(String[] args) {
    Point p1 = new Point(1.0, 1.2);
    Point p2 = new Point(1.0, 1.3);
    HashSet<Point> set = new HashSet<Point>();
    if (!set.contains(p1)) set.add(p1);
    if (!set.contains(p2)) set.add(p2);

    System.out.println(set);
  }
}
