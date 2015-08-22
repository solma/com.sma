package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.IntervalT;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.Interview;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.misc.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Give a set of intervals:
 * find the time period in which most intervals overlap.
 * O(nlogn) time
 */
@Tag(algs = Greedy, dl = D3, dss = {IntervalT}, references = Interview)
public class MostOverlappedTimePeriod {

  // time period does not have to be an interval in the list
  int[] mostOverlappedTimePeriod(List<Interval> intervals) {
    List<EndPoint> points = new ArrayList<>(intervals.size() * 2);
    for (Interval itvl : intervals) {
      points.add(new EndPoint(itvl.start, true, itvl));
      points.add(new EndPoint(itvl.end, false, itvl));
    }
    Collections.sort(points);
    //System.out.println(points);

    int[] result = new int[3];
    int cnt = 0;
    for (EndPoint p : points) {
      if (p.isStart) {
        cnt++;
        if (cnt > result[0]) {
          result[0] = cnt;
          result[1] = p.interval.start;
          result[2] = p.interval.end;
        }
      } else {
        cnt--;
      }
    }
    return result;
  }
}

class EndPoint implements Comparable<EndPoint> {
  int time;
  boolean isStart;
  Interval interval;

  public EndPoint(int time, boolean isStart, Interval interval) {
    this.time = time;
    this.isStart = isStart;
    this.interval = interval;
  }

  @Override
  public int compareTo(EndPoint that) {
    return time - that.time + (!isStart ? 1 : -1);
  }

  @Override
  public String toString() {
    return time + " " + isStart;
  }
}
