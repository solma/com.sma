package com.shuoma.ds.misc;

import java.util.ArrayList;

public class Interval implements Comparable<Interval> {
  public double start;
  public double end;
  public double weight;

  public Interval(double start, double end) {
    this.start = start;
    this.end = end;
  }

  public Interval(double start, double end, double weight) {
    this(start, end);
    this.weight = weight;
  }

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

  /**
   * @param list sorted interval list
   * @return the last interval whose end is no greater than the start of this interval
   */
  public int bisect(ArrayList<Interval> list) {
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

  @Override
  public String toString() {
    return toString(true);
  }

  public String toString(boolean rangeOnly) {
    String ret;
    if (start - (int) start == .0 && end - (int) end == .0)
      ret = (int) start + "~" + (int) end;
    else
      ret = start + "~" + end;

    if (!rangeOnly) ret = "(" + ret + " : " + weight + ")";
    return ret;
  }
}
