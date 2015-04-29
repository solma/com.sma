package com.shuoma.alg;

public class Interval {

  public static class IntervalEndPoint {
    int val;
    int isStart;
    int intervalIdx;

    public IntervalEndPoint(int val, int isStart, int intervalIdx) {
      this.val = val;
      this.isStart = isStart;
      this.intervalIdx = intervalIdx;
    }
  }

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
