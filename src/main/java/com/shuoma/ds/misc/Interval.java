package com.shuoma.ds.misc;

public class Interval {
  public int start;
  public int end;
  public double value;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public Interval(int start, int end, double value) {
    this(start, end);
    this.value = value;
  }

  public boolean include(Interval that) {
    return start <= that.start && end >= that.end;
  }

  public boolean overlap (Interval that) {
    return start <= that.end && end >= that.start;
  }

  @Override
  public String toString() {
    return value > 0 ? toString(true) : toString(false);
  }

  public String toString(boolean showValue) {
    String ret = start + "~" + end;
    return !showValue ? ret : ("(" + ret + " : " + value + ")");
  }
}
