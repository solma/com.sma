package com.shuoma.ds.misc;

import java.util.List;

public class Interval implements Comparable<Interval> {
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

  /** sort based on end time then by start time. */
  @Override
  public int compareTo(Interval o) {
    double endDiff = end - o.end;
    double startDiff = start - o.start;
    if (endDiff > 0)
      return 1;
    else {
      if (endDiff < 0)
        return -1;
      else {
        if (startDiff > 0)
          return 1;
        else {
          if (startDiff < 0) return -1;
          return 0;
        }
      }
    }
  }

  @Override
  public String toString() {
    return weight > 0 ? toString(false) : toString(true);
  }

  private String toString(boolean rangeOnly) {
    String ret;
    if (start - (int) start == .0 && end - (int) end == .0)
      ret = (int) start + "~" + (int) end;
    else
      ret = start + "~" + end;

    if (!rangeOnly) ret = "(" + ret + " : " + weight + ")";
    return ret;
  }
}
