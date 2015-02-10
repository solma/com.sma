package com.shuoma.ds.geometry;

public class Rectangle {
  Point bottomRight, topLeft;

  public Rectangle(Point topLeft, Point bottomRight) {
    this.bottomRight = bottomRight;
    this.topLeft = topLeft;
  }

  public Rectangle(int[] topLeft, int[] bottomRight) {
    this.bottomRight = new Point(bottomRight[0], bottomRight[1]);
    this.topLeft = new Point(topLeft[0], topLeft[1]);
  }

  @Override
  public String toString() {
    return topLeft + " ~ " + bottomRight;
  }
}
