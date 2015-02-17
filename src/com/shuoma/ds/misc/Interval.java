package com.shuoma.ds.misc;

import java.util.List;

public class Interval {
  public int start;
  public int end;
  public double weight;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public Interval(int start, int end, double weight) {
    this(start, end);
    this.weight = weight;
  }

  /**
   * @param list interval list sorted by end time
   * @return the last interval whose end is no greater than the start of this interval
   */
  public int bisect(List<Interval> list) {
    int l = -1, r = list.size();
    while (l + 1 != r) {
      int m = l + (r - l) / 2;
      if (list.get(m).end > start)
        r = m;
      else
        l = m;
    }
    return l;
  }

  public boolean overlap (Interval other) {
    return start <= other.end && end >= other.start;
  }

  @Override
  public String toString() {
    return weight > 0 ? toString(false) : toString(true);
  }

  private String toString(boolean rangeOnly) {
    String ret = start + "~" + end;
    return rangeOnly ? ret : ("(" + ret + " : " + weight + ")");
  }
}
