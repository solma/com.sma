package com.shuoma.ds.geometry;

import java.util.HashSet;

public class Point {

  public static void main(String[] args) {
    Point p1 = new Point(1.0, 1.2);
    Point p2 = new Point(1.0, 1.3);
    HashSet<Point> set = new HashSet<>();
    if (!set.contains(p1)) set.add(p1);
    if (!set.contains(p2)) set.add(p2);

    System.out.println(set);
  }

  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static Point add(Point p1, Point p2) {
    return new Point(p1.x + p2.x, p1.y + p2.y);
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.y - p2.y, 2) + Math.pow(p1.x - p2.x, 2));
  }

  public static double dot(Point p1, Point p2) {
    return p1.x * p2.x + p1.y * p2.y;
  }

  @Override
  public boolean equals(Object p) {
    return (x == ((Point) p).x) && (y == ((Point) p).y);
  }

  @Override
  public int hashCode() {
    return (int) (x + y);
  }

  public static Point minus(Point p1, Point p2) {
    return new Point(p1.x - p2.x, p1.y - p2.y);
  }

  public static Point multiply(double d, Point p1) {
    return new Point(p1.x * d, p1.y * d);
  }

  @Override
  public String toString() {
    return x + "," + y;
  }
}
