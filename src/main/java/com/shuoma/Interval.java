package com.shuoma;

public class Interval {
  int start;
  int end;

  Interval(int s, int e) {
    start = s;
    end = e;
  }

  @Override
  public String toString() {
    return "(" + start + ',' + end + ')';
  }
}
